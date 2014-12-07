var zTree1;
var setting;

var addCount = 10;

function hideRMenu() {
		$("#rMenu").hide();
	}

function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type=="root") {
			$("#m_del").hide();
			$("#m_check").hide();
			$("#m_unCheck").hide();
		}
		$("#rMenu").css({"top":y+"px", "left":(x+20)+"px", "display":"block"});
	}


function zTreeOnRightClick(event, treeId, treeNode){
	if (!treeNode) {
			zTree1.cancelSelectedNode();
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			if (treeNode.newrole && event.target.tagName != "a" && $(event.target).parents("a").length == 0) {
				zTree1.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else {
				zTree1.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}
}

function addTreeNode() {
		zTree1.addNodes(zTree1.getSelectedNode(), { id:addCount++,name:"create" + (addCount++)});
		hideRMenu();
		$("#MainArea1").hide();
		$("#MainArea").show();
	}
	function removeTreeNode() {
		var node = zTree1.getSelectedNode();
		if (node) {
			if (node.nodes && node.nodes.length > 0) {
				var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
				if (confirm(msg)==true){
					zTree1.removeNode(node);
				}
			} else {
				zTree1.removeNode(node);
			}
		}
		hideRMenu();
	}


$(document).ready(function(){
    setting = {
		isSimpleData: true,
		treeNodeKey: "id",
		treeNodeParentKey: "pid",
		showLine: true,
		root:{ 
			isRoot:true,
			nodes:[]
		},
		callback: {
			rightClick: zTreeOnRightClick,
			click:onzTreeClick
		}
    };
	zTree1 = $("#kynamicTree").zTree(setting, kynamicTreeDate);
});

function onzTreeClick(){
	$("#MainArea1").show();
	$("#MainArea").hide();
}

function showVersion(){
	$("#MainArea1").hide();
	$("#MainArea").show();
}
	