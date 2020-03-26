/**

 @Name: �����

 */
 
layui.define('fly', function(exports){

  var $ = layui.jquery;
  var layer = layui.layer;
  var util = layui.util;
  var laytpl = layui.laytpl;
  var form = layui.form;
  var fly = layui.fly;
  
  var gather = {}, dom = {
    jieda: $('#jieda')
    ,content: $('#L_content')
    ,jiedaCount: $('#jiedaCount')
  };

  //����ר��ѡ��
  form.on('select(column)', function(obj){
    var value = obj.value
    ,elemQuiz = $('#LAY_quiz')
    ,tips = {
      tips: 1
      ,maxWidth: 250
      ,time: 10000
    };
    elemQuiz.addClass('layui-hide');
    if(value === '0'){
      layer.tips('�������Ϣ����������ø��õĴ�', obj.othis, tips);
      elemQuiz.removeClass('layui-hide');
    } else if(value === '99'){
      layer.tips('ϵͳ��ԡ��������͵��������Է��ǽ�������������Ҫ��ˣ�ͨ���󷽿�չʾ', obj.othis, tips);
    }
  });

  //�ύ�ش�
  fly.form['/jie/reply/'] = function(data, required){
    var tpl = '<li>\
      <div class="detail-about detail-about-reply">\
        <a class="fly-avatar" href="/u/{{ layui.cache.user.uid }}" target="_blank">\
          <img src="{{= d.user.avatar}}" alt="{{= d.username}}">\
        </a>\
        <div class="fly-detail-user">\
          <a href="/u/xx" target="_blank" class="fly-link">\
            <cite>{{d.user.username}}</cite>\
          </a>\
        </div>\
        <div class="detail-hits">\
          <span>�ո�</span>\
        </div>\
      </div>\
      <div class="detail-body jieda-body photos">\
        {{ d.content}}\
      </div>\
    </li>'
    data.content = fly.content(data.content);
    laytpl(tpl).render($.extend(data, {
      user: layui.cache.user
    }), function(html){
      required[0].value = '';
      dom.jieda.find('.fly-none').remove();
      dom.jieda.append(html);
      
      var count = dom.jiedaCount.text()|0;
      dom.jiedaCount.html(++count);
    });
  };

  //������
  gather.jieAdmin = {
    //ɾ���
    del: function(div){
      layer.confirm('ȷ��ɾ�������ô��', function(index){
        layer.close(index);
        fly.json('/lt_56/delete', {
          articleId: div.data('id')
        }, function(res){
        	
          if(res.code === 0){
              layer.msg("ɾ���ɹ�");
              location.reload();
          } else {
            layer.msg(res.msg);
          }
        });
      });
    }
    
    //�����ö���״̬
    ,set: function(div){
      var othis = $(this);
      fly.json('/api/jie-set/', {
        id: div.data('id')
        ,rank: othis.attr('rank')
        ,field: othis.attr('field')
      }, function(res){
        if(res.status === 0){
          location.reload();
        }
      });
    }

    //�ղ�
    ,collect: function(div){
      var othis = $(this), type = othis.data('type');
      fly.json('/collection/'+ type +'/', {
        cid: div.data('id')
      }, function(res){
        if(type === 'add'){
          othis.data('type', 'remove').html('ȡ���ղ�').addClass('layui-btn-danger');
        } else if(type === 'remove'){
          othis.data('type', 'add').html('�ղ�').removeClass('layui-btn-danger');
        }
      });
    }
  };

  $('body').on('click', '.jie-admin', function(){
    var othis = $(this), type = othis.attr('type');
    gather.jieAdmin[type] && gather.jieAdmin[type].call(this, othis.parent());
  });

  //�첽��Ⱦ
  var asyncRender = function(){
    var div = $('.fly-admin-box'), jieAdmin = $('#LAY_jieAdmin');
    //��ѯ�����Ƿ��ղ�
    if(jieAdmin[0] && layui.cache.user.uid != -1){
      fly.json('/collection/find/', {
        cid: div.data('id')
      }, function(res){
        jieAdmin.append('<span class="layui-btn layui-btn-xs jie-admin '+ (res.data.collection ? 'layui-btn-danger' : '') +'" type="collect" data-type="'+ (res.data.collection ? 'remove' : 'add') +'">'+ (res.data.collection ? 'ȡ���ղ�' : '�ղ�') +'</span>');
      });
    }
  }();

  //������
  gather.jiedaActive = {
    zan: function(li){ //��
      var othis = $(this), ok = othis.hasClass('zanok');
    
      fly.json('/lt_56/goodReply', {
        ok: ok
        ,articleID: li.data('article'),
        commentID: li.data('comment')
      }, function(res){
        if(res.status === 0){
          var zans = othis.find('em').html()|0;
          othis[ok ? 'removeClass' : 'addClass']('zanok');
          othis.find('em').html(ok ? (--zans) : (++zans));
        } else {
          layer.msg(res.msg);
        }
      });
    }
    ,reply: function(li){ //�ظ�
      var val = dom.content.val();
      var aite = '@'+ li.find('.fly-detail-user cite').text().replace(/\s/g, '');
      dom.content.focus()
      if(val.indexOf(aite) !== -1) return;
      dom.content.val(aite +' ' + val);
    }
    ,accept: function(li){ //����
      var othis = $(this);
      layer.confirm('�Ƿ���ɸûش�Ϊ��Ѵ𰸣�', function(index){
        layer.close(index);
        fly.json('/api/jieda-accept/', {
          id: li.data('id')
        }, function(res){
          if(res.status === 0){
            $('.jieda-accept').remove();
            li.addClass('jieda-daan');
            li.find('.detail-about').append('<i class="iconfont icon-caina" title="��Ѵ�"></i>');
          } else {
            layer.msg(res.msg);
          }
        });
      });
    }
    ,edit: function(li){ //�༭
      fly.json('/jie/getDa/', {
        id: li.data('id')
      }, function(res){
        var data = res.rows;
        layer.prompt({
          formType: 2
          ,value: data.content
          ,maxlength: 100000
          ,title: '�༭����'
          ,area: ['728px', '300px']
          ,success: function(layero){
            fly.layEditor({
              elem: layero.find('textarea')
            });
          }
        }, function(value, index){
          fly.json('/jie/updateDa/', {
            id: li.data('id')
            ,content: value
          }, function(res){
            layer.close(index);
            li.find('.detail-body').html(fly.content(value));
          });
        });
      });
    }
    ,del: function(li){ //ɾ��
      layer.confirm('ȷ��ɾ���ûش�ô��', function(index){
        layer.close(index);
        fly.json('/api/jieda-delete/', {
          id: li.data('id')
        }, function(res){
          if(res.status === 0){
            var count = dom.jiedaCount.text()|0;
            dom.jiedaCount.html(--count);
            li.remove();
            //���ɾ������Ѵ�
            if(li.hasClass('jieda-daan')){
              $('.jie-status').removeClass('jie-status-ok').text('�����');
            }
          } else {
            layer.msg(res.msg);
          }
        });
      });    
    }
  };

  $('.jieda-reply span').on('click', function(){
    var othis = $(this), type = othis.attr('type');
    gather.jiedaActive[type].call(this, othis.parents('li'));
  });


  //��λ��ҳ
  if(/\/page\//.test(location.href) && !location.hash){
    var replyTop = $('#flyReply').offset().top - 80;
    $('html,body').scrollTop(replyTop);
  }

  exports('jie', null);
});