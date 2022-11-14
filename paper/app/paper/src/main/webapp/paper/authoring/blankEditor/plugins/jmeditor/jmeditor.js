KindEditor.plugin('jmeditor', function(K) {
    var self = this, name = 'jmeditor', lang = self.lang(name + '.');
    var jme_fid = "math_frame_" + self.id;
    var html = '<div style="margin:10px;">'
            + '<iframe id="' + jme_fid + '" style="width:500px;height:300px;" frameborder="no" src="' + self.basePath + 'plugins/jmeditor/dialogs/mathdialog.html"></iframe>'
            + '</div>';
    self.plugin.jmeditorDialog = function(options) {
        var dialogWidth = 520, dialogHeight = 350;
        var dialog = self.createDialog({
            name: name,
            width: dialogWidth,
            height: dialogHeight,
            title: self.lang(name),
            body: html,
            yesBtn: {
                name: self.lang('yes'),
                click: function(e) {
                    var thedoc = getIFrameDOM(jme_fid);
                    var mathSpan = $("#jme-math", thedoc);
                    var spanBegin = '<span class="mathquill-rendered-math" style="font-size:' + JMEditor.defaultFontSize + ';" >';
                    var hiddenlatex = "<span class='latex' style='display:none;' >"+$("#revert", thedoc).text()+"</span>";
                    var mathHTML =  mathSpan.html();
                    var spanClose =  '</span>';
                    var nbsp = "<span>&nbsp;</span>";
                    if(self.html().indexOf("mathquill-0.9.1/mathquill.css")==-1){
                        self.insertHtml("<link href=\"../../js/common/jmeditor/mathquill-0.9.1/mathquill.css\" rel=\"stylesheet\" type=\"text/css\" />");
                    }
                    self.insertHtml(spanBegin+mathHTML+hiddenlatex+spanClose+nbsp);
                    self.hideDialog();
                    return;
                }
            }
        });
        return dialog;
    };

    self.plugin.jmeditor = {
        edit: function() {
            self.plugin.jmeditorDialog({
            });
        }
    };
    self.clickToolbar(name, self.plugin.jmeditor.edit);

});

function getIFrameDOM(fid){
	var fm = getIFrame(fid);
	return fm.document||fm.contentDocument;
}
function getIFrame(fid){
	return document.frames ? document.frames[fid] : document.getElementById(fid);
}
