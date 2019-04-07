// $(document).ready(function () {

    var menuContent = $(".sidebar-menu");
    var menuList = '${menuList}';
    console.log("menuList : " + menuList);
    if(menuList && menuList.length > 0){
        for(var i = 0 ; i < menuList.length; i++){
            var menu = menuList[i];
            var parentId = menu.parentId;
            if(parentId == '0'){
                // 一级菜单
                var treeView = '<li class="treeview">\n' +
                    '                    <a href="#">\n' +
                    '                        <i class='+menu.icon+'></i> <span>'+menu.menuName+'</span>\n' +
                    '                        <i class="fa fa-angle-left pull-right"></i>\n' +
                    '                    </a>\n' +
                    '                    <ul class="treeview-menu" id='+menu.menuId+'></ul>\n' +
                    '                </li>';

                menuContent.append(treeView);
            }else{
                var treeviewMenu = $('#'+parentId);// 找到上一级菜单
                var children = '<li><a class="multitabs" id='+menu.menuId+' href='+menu.url+' data-type="info"><i class="'+menu.icon+'" style="color: white" aria-hidden="true"></i>'+menu.menuName+'</a></li>';
                if(treeviewMenu){
                    treeviewMenu.append(children);
                }else{
                    menuContent.append(children);
                }

            }

        }
    }


// });


// function craeteParentMenu(menu){
//     var menuContent = $(".sidebar-menu");
//     // 一级菜单
//     var treeView = '<li class="treeview">\n' +
//         '                    <a href="#">\n' +
//         '                        <i class="fa fa-briefcase"></i> <span>'+menu.menuName+'</span>\n' +
//         '                        <i class="fa fa-angle-left pull-right"></i>\n' +
//         '                    </a>\n' +
//         '                    <ul class="treeview-menu" id='+menu.menuId+'></ul>\n' +
//         '                </li>';
//
//     menuContent.append(treeView)
// }


