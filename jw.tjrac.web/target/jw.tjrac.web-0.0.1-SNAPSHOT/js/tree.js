var zTree1;
var setting;

$(document).ready(function(){
    setting = {
		isSimpleData: true,
		treeNodeKey: "id",
		treeNodeParentKey: "pid",
		showLine: true,
		root:{ 
			isRoot:true,
			nodes:[]
		}
    };
	zTree1 = $("#menuTree").zTree(setting, privilegeDate);
});
