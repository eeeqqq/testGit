var zTree1;
var setting;


function onPrivilegeBeforeChange(treeId, treeNode){
    var setting1 = zTree1.getSetting();
    setting1.checkType = {
        "Y": "p",
        "N": "s"
    };
    zTree1.updateSetting(setting1);
}

	function privilegeclick(){
		setting = {
        isSimpleData: true,
        treeNodeKey: "id",
        treeNodeParentKey: "pid",
        checkable: true,
        showLine: true,
        root: {
            isRoot: true,
            nodes: []
        },
        checkType: {
            "Y": "p",
            "N": "s"
        },callback:{
			beforeChange: onPrivilegeBeforeChange
		}
    };
	zTree1 = $("#privilegeTree").zTree(setting, privilegeDate);
	$("#privilegeContent").show();
    $("#privilegeTitle").show();
    $("#userTitle").show();

	}

function privilegeCheckedAll(value, id){
    var setting1 = zTree1.getSetting();
    setting1.checkType = {
        "Y": "ps",
        "N": "ps"
    };
    zTree1.updateSetting(setting1);
    userid = id;
    if (value) {
        zTree1.checkAllNodes(true);
    }
    else {
        zTree1.checkAllNodes(false);
    }
}
