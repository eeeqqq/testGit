if(document.getElementById("loginuser").value == "majinghe"){
var privilegeDate = [ {
    id : '3',
    pid : '1',
    name : '审批流转',
    icon : 'style/images/MenuIcon/FUNC20057.gif',
    isParent : true,
    nodes : [ {
        id : '33',
        pid : '3',
        isParent : false,
        target : 'right',
        url : 'workFlow/formTemplateList',
        icon : 'style/images/MenuIcon/FUNC241000.gif',
        name : '发起申请'
    }, {
        id : '35',
        pid : '3',
        target : 'right',
        isParent : false,
        url : 'workFlow/mySubmittedList',
        icon : 'style/images/MenuIcon/FUNC20029.gif',
        name : '查询状态'
    } ]

} ];
}else if(document.getElementById("loginuser").value == "cailaoshi"){
    var privilegeDate = [ {
        id : '3',
        pid : '1',
        name : '审批流转',
        icon : 'style/images/MenuIcon/FUNC20057.gif',
        isParent : true,
        nodes : [ {
            id : '34',
            pid : '3',
            isParent : false,
            target : 'right',
            url : 'workFlow/myTaskList',
            icon : 'style/images/MenuIcon/FUNC20029.gif',
            name : '审批处理'
        } ]

    } ];
}else{
    var privilegeDate = [ {
        id : '3',
        pid : '1',
        name : '审批流转',
        icon : 'style/images/MenuIcon/FUNC20057.gif',
        isParent : true,
        nodes : [ {
            id : '31',
            pid : '3',
            isParent : false,
            icon : 'style/images/MenuIcon/manager.gif',
            target : 'right',
            url : 'pdmanager/versionslist',
            name : '审批流程管理'
        }, {
            id : '32',
            pid : '3',
            isParent : false,
            target : 'right',
            url : 'formtemplate/formTemplateList',
            icon : 'style/images/MenuIcon/formmodel.gif',
            name : '表单模板管理'
        }, {
            id : '34',
            pid : '3',
            isParent : false,
            target : 'right',
            url : 'workFlow/myTaskList',
            icon : 'style/images/MenuIcon/FUNC20029.gif',
            name : '审批处理'
        }]

    } ];
}
