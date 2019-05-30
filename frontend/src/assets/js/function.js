$(function(){
    var url = window.location.pathname,
    urlRegExp = new RegExp(url.replace(/\/$/,'') + "$");
    console.log(url);
    
    $("#site-navigation a").each(function(){
        
        var id = this.id;
        if(urlRegExp.test(this.href.replace(/\/$/,''))){
            $('#'+id+' .nav-li').addClass('activeNav');
        }
    });
});