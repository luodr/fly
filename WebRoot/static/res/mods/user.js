/**

 @Name: �û�ģ��

 */
 
layui.define(['laypage', 'fly', 'element', 'flow'], function(exports){

  var $ = layui.jquery;
  var layer = layui.layer;
  var util = layui.util;
  var laytpl = layui.laytpl;
  var form = layui.form;
  var laypage = layui.laypage;
  var fly = layui.fly;
  var flow = layui.flow;
  var element = layui.element;
  var upload = layui.upload;

  var gather = {}, dom = {
    mine: $('#LAY_mine')
    ,mineview: $('.mine-view')
    ,minemsg: $('#LAY_minemsg')
    ,infobtn: $('#LAY_btninfo')
  };

  //�ҵ��������
  var elemUC = $('#LAY_uc'), elemUCM = $('#LAY_ucm');
  gather.minelog = {};
  gather.mine = function(index, type, url){
    var tpl = [
      //���
      '{{# for(var i = 0; i < d.rows.length; i++){ }}\
      <li>\
        {{# if(d.rows[i].collection_time){ }}\
          <a class="jie-title" href="/jie/{{d.rows[i].id}}/" target="_blank">{{= d.rows[i].title}}</a>\
          <i>{{ d.rows[i].collection_time }} �ղ�</i>\
        {{# } else { }}\
          {{# if(d.rows[i].status == 1){ }}\
          <span class="fly-jing layui-hide-xs">��</span>\
          {{# } }}\
          {{# if(d.rows[i].accept >= 0){ }}\
            <span class="jie-status jie-status-ok">�ѽ�</span>\
          {{# } else { }}\
            <span class="jie-status">δ��</span>\
          {{# } }}\
          {{# if(d.rows[i].status == -1){ }}\
            <span class="jie-status">�����</span>\
          {{# } }}\
          <a class="jie-title" href="/jie/{{d.rows[i].id}}/" target="_blank">{{= d.rows[i].title}}</a>\
          <i class="layui-hide-xs">{{ layui.util.timeAgo(d.rows[i].time, 1) }}</i>\
          {{# if(d.rows[i].accept == -1){ }}\
          <a class="mine-edit layui-hide-xs" href="/jie/edit/{{d.rows[i].id}}" target="_blank">�༭</a>\
          {{# } }}\
          <em class="layui-hide-xs">{{d.rows[i].hits}}��/{{d.rows[i].comment}}��</em>\
        {{# } }}\
      </li>\
      {{# } }}'
    ];

    var view = function(res){
      var html = laytpl(tpl[0]).render(res);
      dom.mine.children().eq(index).find('span').html(res.count);
      elemUCM.children().eq(index).find('ul').html(res.rows.length === 0 ? '<div class="fly-msg">û���������</div>' : html);
    };

    var page = function(now){
      var curr = now || 1;
      if(gather.minelog[type + '-page-' + curr]){
        view(gather.minelog[type + '-page-' + curr]);
      } else {
        //���ղص���
        if(type === 'collection'){
          var nums = 10; //ÿҳ���ֵ�������
          fly.json(url, {}, function(res){
            res.count = res.rows.length;

            var rows = layui.sort(res.rows, 'collection_timestamp', 'desc')
            ,render = function(curr){
              var data = []
              ,start = curr*nums - nums
              ,last = start + nums - 1;

              if(last >= rows.length){
                last = curr > 1 ? start + (rows.length - start - 1) : rows.length - 1;
              }

              for(var i = start; i <= last; i++){
                data.push(rows[i]);
              }

              res.rows = data;
              
              view(res);
            };

            render(curr)
            gather.minelog['collect-page-' + curr] = res;

            now || laypage.render({
              elem: 'LAY_page1'
              ,count: rows.length
              ,curr: curr
              ,jump: function(e, first){
                if(!first){
                  render(e.curr);
                }
              }
            });
          });
        } else {
          fly.json('/api/'+ type +'/', {
            page: curr
          }, function(res){
            view(res);
            gather.minelog['mine-jie-page-' + curr] = res;
            now || laypage.render({
              elem: 'LAY_page'
              ,count: res.count
              ,curr: curr
              ,jump: function(e, first){
                if(!first){
                  page(e.curr);
                }
              }
            });
          });
        }
      }
    };

    if(!gather.minelog[type]){
      page();
    }
  };

  if(elemUC[0]){
    layui.each(dom.mine.children(), function(index, item){
      var othis = $(item)
      gather.mine(index, othis.data('type'), othis.data('url'));
    });
  }

  //��ʾ��ǰtab
  if(location.hash){
    element.tabChange('user', location.hash.replace(/^#/, ''));
  }

  element.on('tab(user)', function(){
    var othis = $(this), layid = othis.attr('lay-id');
    if(layid){
      location.hash = layid;
    }
  });

  //����ip��ȡ����
  if($('#L_city').val() === ''){
    $.getScript('http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js', function(){
      $('#L_city').val(remote_ip_info.city||'');
    });
  }

  //�ϴ�ͼƬ
  if($('.upload-img')[0]){
    layui.use('upload', function(upload){
      var avatarAdd = $('.avatar-add');

      upload.render({
        elem: '.upload-img'
        ,url: '/lt_56/upload'
        ,size: 50
        ,before: function(){
          avatarAdd.find('.loading').show();
        }
        ,done: function(res){
          if(res.status == 0){
            location.reload();
          } else {
            layer.msg(res.msg, {icon: 5});
          }
          avatarAdd.find('.loading').hide();
        }
        ,error: function(){
          avatarAdd.find('.loading').hide();
        }
      });
    });
  }

  //����ƽ̨
  if($('#LAY_coop')[0]){

    //��Դ�ϴ�
    $('#LAY_coop .uploadRes').each(function(index, item){
      var othis = $(this);
      upload.render({
        elem: item
        ,url: '/api/upload/cooperation/?filename='+ othis.data('filename')
        ,accept: 'file'
        ,exts: 'zip'
        ,size: 30*1024
        ,before: function(){
          layer.msg('�����ϴ�', {
            icon: 16
            ,time: -1
            ,shade: 0.7
          });
        }
        ,done: function(res){
          if(res.code == 0){
            layer.msg(res.msg, {icon: 6})
          } else {
            layer.msg(res.msg)
          }
        }
      });
    });

    //��Чչʾ
    var effectTpl = ['{{# layui.each(d.data, function(index, item){ }}'
    ,'<tr>'
      ,'<td><a href="/u/{{ item.uid }}" target="_blank" style="color: #01AAED;">{{ item.uid }}</a></td>'
      ,'<td>{{ item.authProduct }}</td>'
      ,'<td>��{{ item.rmb }}</td>'
      ,'<td>{{ item.create_time }}</td>'
      ,'</tr>'
    ,'{{# }); }}'].join('');

    var effectView = function(res){
      var html = laytpl(effectTpl).render(res);
      $('#LAY_coop_effect').html(html);
      $('#LAY_effect_count').html('�㹲�� <strong style="color: #FF5722;">'+ (res.count||0) +'</strong> �ʺ�����Ȩ����');
    };

    var effectShow = function(page){
      fly.json('/cooperation/effect', {
        page: page||1
      }, function(res){
        effectView(res);
        laypage.render({
          elem: 'LAY_effect_page'
          ,count: res.count
          ,curr: page
          ,jump: function(e, first){
            if(!first){
              effectShow(e.curr);
            }
          }
        });
      });
    };

    effectShow();

  }

  //�ύ�ɹ���ˢ��
  fly.form['set-mine'] = function(data, required){
    layer.msg('�޸ĳɹ�', {
      icon: 1
      ,time: 1000
      ,shade: 0.1
    }, function(){
      location.reload();
    });
  }

  //�ʺŰ�
  $('.acc-unbind').on('click', function(){
    var othis = $(this), type = othis.attr('type');
    layer.confirm('����Ҫ���'+ ({
      qq_id: 'QQ'
      ,weibo_id: '΢��'
    })[type] + '��', {icon: 5}, function(){
      fly.json('/api/unbind', {
        type: type
      }, function(res){
        if(res.status === 0){
          layer.alert('�ѳɹ����', {
            icon: 1
            ,end: function(){
              location.reload();
            }
          });
        } else {
          layer.msg(res.msg);
        }
      });
    });
  });


  //�ҵ���Ϣ
  gather.minemsg = function(){
    var delAll = $('#LAY_delallmsg')
    ,tpl = '{{# var len = d.rows.length;\
    if(len === 0){ }}\
      <div class="fly-none">����ʱû��������Ϣ</div>\
    {{# } else { }}\
      <ul class="mine-msg">\
      {{# for(var i = 0; i < len; i++){ }}\
        <li data-id="{{d.rows[i].id}}">\
          <blockquote class="layui-elem-quote">{{ d.rows[i].content}}</blockquote>\
          <p><span>{{d.rows[i].time}}</span><a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-danger fly-delete">ɾ��</a></p>\
        </li>\
      {{# } }}\
      </ul>\
    {{# } }}'
    ,delEnd = function(clear){
      if(clear || dom.minemsg.find('.mine-msg li').length === 0){
        dom.minemsg.html('<div class="fly-none">����ʱû��������Ϣ</div>');
      }
    }
    
    
    /*
    fly.json('/message/find/', {}, function(res){
      var html = laytpl(tpl).render(res);
      dom.minemsg.html(html);
      if(res.rows.length > 0){
        delAll.removeClass('layui-hide');
      }
    });
    */
    
    //�Ķ���ɾ��
    dom.minemsg.on('click', '.mine-msg li .fly-delete', function(){
      var othis = $(this).parents('li'), id = othis.data('id');
      fly.json('/message/remove/', {
        id: id
      }, function(res){
        if(res.status === 0){
          othis.remove();
          delEnd();
        }
      });
    });

    //ɾ��ȫ��
    $('#LAY_delallmsg').on('click', function(){
      var othis = $(this);
      layer.confirm('ȷ�������', function(index){
        fly.json('/message/remove/', {
          all: true
        }, function(res){
          if(res.status === 0){
            layer.close(index);
            othis.addClass('layui-hide');
            delEnd(true);
          }
        });
      });
    });

  };

  dom.minemsg[0] && gather.minemsg();

  exports('user', null);
  
});