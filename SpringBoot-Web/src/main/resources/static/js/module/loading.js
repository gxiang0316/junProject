jQuery.loading={
    show:function () {
        // data-backdrop="static" ：点击外部不消失
        var loadingHtml = '<div class="modal" id="loadingModal" ' +
            'tabindex="-1" role="dialog" aria-labelledby="imgModalLabel" aria-hidden="true" ' +
            'data-backdrop="false" data-keyboard="false" style="z-index: 9990; display: none;">\n' +
            '    <div class="modal-dialog" style="padding-right: 15px;">\n' +
            '        <div>\n' +
            '            <div><img src='+'imgs/Spinner-1s-80px.gif></div>\n' +
            '            \n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>';

        $("body").append(loadingHtml);

        $('#loadingModal').modal('show');

    },

    hide:function () {
        $('#loadingModal').modal('hide');
    }
}

/**
show:function () {
    var loadingHtml = '<div class="modal fade" id="loadingModal">\n' +
        '    <div class="modal-dialog"">\n' +
        '        <div class="modal-content">\n' +
        '            <div style="width: 100px;\n' +
        '                        height: 100px;\n' +
        '                        position: relative;\n' +
        '                        margin: 0 auto;\n' +
        '                        ">\n' +
        '                <span style="display: inline-block;\n' +
        '                        width: 20px;\n' +
        '                        height: 20px;\n' +
        '                        border-radius: 50%;\n' +
        '                        background: #1c391c;\n' +
        '                        position: absolute;\n' +
        '                        -webkit-animation: load 0.85s ease infinite;\n' +
        '                        left: 0;\n' +
        '                        top: 50%;\n' +
        '                        margin-top:-10px;\n' +
        '                        -webkit-animation-delay:0.13s;"></span>\n' +
        '                <span style="display: inline-block;\n' +
        '                        width: 20px;\n' +
        '                        height: 20px;\n' +
        '                        border-radius: 50%;\n' +
        '                        background: #1c391c;\n' +
        '                        position: absolute;\n' +
        '                        -webkit-animation: load 0.85s ease infinite;\n' +
        '                        left: 14px;\n' +
        '                        top: 14px;\n' +
        '                        -webkit-animation-delay:0.26s;"></span>\n' +
        '                <span style="display: inline-block;\n' +
        '                        width: 20px;\n' +
        '                        height: 20px;\n' +
        '                        border-radius: 50%;\n' +
        '                        background: #1c391c;\n' +
        '                        position: absolute;\n' +
        '                        -webkit-animation: load 0.85s ease infinite;\n' +
        '                        left: 50%;\n' +
        '                        top: 0;\n' +
        '                        margin-left: -10px;\n' +
        '                        -webkit-animation-delay:0.39s;"></span>\n' +
        '                <span style="display: inline-block;\n' +
        '                        width: 20px;\n' +
        '                        height: 20px;\n' +
        '                        border-radius: 50%;\n' +
        '                        background: #1c391c;\n' +
        '                        position: absolute;\n' +
        '                        -webkit-animation: load 0.85s ease infinite;\n' +
        '                        top: 14px;\n' +
        '                        right:14px;\n' +
        '                        -webkit-animation-delay:0.52s;"></span>\n' +
        '                <span style="display: inline-block;\n' +
        '                        width: 20px;\n' +
        '                        height: 20px;\n' +
        '                        border-radius: 50%;\n' +
        '                        background: #1c391c;\n' +
        '                        position: absolute;\n' +
        '                        -webkit-animation: load 0.85s ease infinite;\n' +
        '                        right: 0;\n' +
        '                        top: 50%;\n' +
        '                        margin-top:-10px;\n' +
        '                        -webkit-animation-delay:0.65s;"></span>\n' +
        '                <span style="display: inline-block;\n' +
        '                        width: 20px;\n' +
        '                        height: 20px;\n' +
        '                        border-radius: 50%;\n' +
        '                        background: #1c391c;\n' +
        '                        position: absolute;\n' +
        '                        -webkit-animation: load 0.85s ease infinite;\n' +
        '                        right: 14px;\n' +
        '                        bottom:14px;\n' +
        '                        -webkit-animation-delay:0.78s;"></span>\n' +
        '                <span style="display: inline-block;\n' +
        '                        width: 20px;\n' +
        '                        height: 20px;\n' +
        '                        border-radius: 50%;\n' +
        '                        background: #1c391c;\n' +
        '                        position: absolute;\n' +
        '                        -webkit-animation: load 0.85s ease infinite;\n' +
        '                        bottom: 0;\n' +
        '                        left: 50%;\n' +
        '                        margin-left: -10px;\n' +
        '                        -webkit-animation-delay:0.91s;"></span>\n' +
        '                <span style="display: inline-block;\n' +
        '                        width: 20px;\n' +
        '                        height: 20px;\n' +
        '                        border-radius: 50%;\n' +
        '                        background: #1c391c;\n' +
        '                        position: absolute;\n' +
        '                        -webkit-animation: load 0.85s ease infinite;\n' +
        '                        bottom: 14px;\n' +
        '                        left: 14px;\n' +
        '                        -webkit-animation-delay:0.85s;"></span>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>';

    $("body").append(loadingHtml);

    var rule = '@-webkit-keyframes load{0%{ -webkit-transform: scale(1.2);opacity: 1;}\\n'+
        '100%{-webkit-transform: scale(.3); opacity: 0.5;}}';

    var style = document.createElement('style');
    style.type = 'text/css';
    style.innerHTML = '';
    document.getElementsByTagName('head')[0].appendChild(style);
    this.stylesheet = document.styleSheets[document.styleSheets.length-1];

    try {
        this.stylesheet.insertRule( rule , this.stylesheet.rules.length);
    } catch (e) {
    };

    $('#loadingModal').modal('show')

}
*/


