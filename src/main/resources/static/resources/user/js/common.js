function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth  
							+",height="+ WinHeight +",top="+ wintop +",left=" 
							+ winleft +",resizable=yes"  );
	win.focus() ; 
}


function list_go(page,url){
	
	
	$("form#jobForm input[name='page']").val(page);
	$("form#jobForm input[name='perPageNum']").val($('select[name="perPageNum"]').val());
	$("form#jobForm input[name='searchType']").val($('select[name="searchType"]').val());
	$("form#jobForm input[name='keyword']").val($('input[name="keyword"]').val());
	$("form#jobForm input[name='startday']").val($('input[name="startday"]').val());
	$("form#jobForm input[name='endday']").val($('input[name="endday"]').val());
	
	$('form#jobForm').attr({
		action:url,
		method:'get'
	}).submit();
}



//팝업창 닫기
function CloseWindow(parentURL){
	
	window.opener.location.reload(true);		
	window.close();
}

  function MemberPictureThumb(contextPath) {
    for (var target of document.querySelectorAll('.manPicture')) {
      var id = target.getAttribute('data-id');
      target.style.backgroundImage = "url('/ers/manager/member/getPicture?id=" + id + "')";
      target.style.backgroundPosition = "center";
      target.style.backgroundRepeat = "no-repeat";
      target.style.backgroundSize = "cover";
    }
  }


 function lsupporterPictureThumb(contextPath) {
    for (var target of document.querySelectorAll('.lsuppPicture')) {
      var wid = target.getAttribute('data-id');
      target.style.backgroundImage = "url('/ers/lsupporter/getLsuppPicture?wid=" + wid + "')";
      target.style.backgroundPosition = "center";
      target.style.backgroundRepeat = "no-repeat";
      target.style.backgroundSize = "cover";
    }
  }


var contextPath="";
function summernote_go(target,context){
	contextPath=context;
	
	target.summernote({
		placeholder:'여기에 내용을 적으세요.',
		lang:'ko-KR',
		height:250,
		disableResizeEditor: true,
		callbacks:{
			onImageUpload : function(files, editor, welEditable) {
				//alert("image click");
				for(var file of files){
					//alert(file.name);
					if(file.name.substring(file.name.lastIndexOf(".")+1).toUpperCase() != "JPG"){
						alert("JPG 이미지형식만 가능합니다.");
						return;
					}
					if(file.size > 1024*1024*1){
						alert("이미지는 1MB 미만입니다.");
						return;
					}	
				}
				
				for (var file of files) {
					alert("시작");
					sendFile(file,this);
				}
			},
			onMediaDelete : function(target) {
				//alert(target[0].src);
				deleteFile(target[0].src);	
			}
	
		}
	});    		
}

function sendFile(file,el){
	var form_data = new FormData();
	form_data.append("file", file); 
	$.ajax({
		url: contextPath+'/uploadImg',
    	data: form_data,
    	type: "POST",	    	
    	contentType: false,	    	
    	processData: false,
    	success: function(img_url) {    		
    		$(el).summernote('editor.insertImage', img_url);
    	},
    	error:function(error){
    		alert("실패?");
    	}
	});
}

function deleteFile(src) {	
	var deleteURL = src.replace("getImg","deleteImg");
	$.ajax({
		url:deleteURL,
		type:"get",
		success:function(data){
			console.log(data);
		}
	});
}


