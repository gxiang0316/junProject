!function (a) {
    "use strict";
    "function" == typeof define && define.amd ? define(["jquery", "../grid.base"], a) : a(jQuery)
}(function (a) {
    a.jgrid = a.jgrid || {}, a.jgrid.hasOwnProperty("regional") || (a.jgrid.regional = []), a.jgrid.regional.cn = {
        defaults: {
            recordtext: "第{0}到第{1}条　共 {2} 条",
            emptyrecords: "没有记录！",
            loadtext: "读取中...",
            savetext: "保存中...",
            pgtext: "第{0}页　共{1}页",
            pgfirst: "第一页",
            pglast: "最后一页",
            pgnext: "下一页",
            pgprev: "上一页",
            pgrecs: "每页记录数",
            showhide: "切换 展开 折叠 表格",
            pagerCaption: "表格::页面设置",
            pageText: "Page:",
            recordPage: "每页记录数",
            nomorerecs: "没有更多记录...",
            scrollPullup: "加载更多...",
            scrollPulldown: "刷新...",
            scrollRefresh: "滚动刷新..."
        },
        search: {
            caption: "搜索...",
            Find: "查找",
            Reset: "重置",
            odata: [{oper: "eq", text: "等于　　"}, {oper: "ne", text: "不等于　"}, {
                oper: "lt",
                text: "小于　　"
            }, {oper: "le", text: "小于等于"}, {oper: "gt", text: "大于　　"}, {
                oper: "ge",
                text: "大于等于"
            }, {oper: "bw", text: "开头是"}, {oper: "bn", text: "开头不是"}, {
                oper: "in",
                text: "属于　　"
            }, {oper: "ni", text: "不属于"}, {oper: "ew", text: "结尾是"}, {
                oper: "en",
                text: "结尾不是"
            }, {oper: "cn", text: "包含　　"}, {oper: "nc", text: "不包含"}, {
                oper: "nu",
                text: "为空"
            }, {oper: "nn", text: "不为空"}, {oper: "bt", text: "区间"}],
            groupOps: [{op: "AND", text: "满足所有条件"}, {op: "OR", text: "满足任一条件"}],
            operandTitle: "单击进行搜索。",
            resetTitle: "重置搜索条件",
            addsubgrup: "添加条件组",
            addrule: "添加条件",
            delgroup: "删除条件组",
            delrule: "删除条件"
        },
        edit: {
            addCaption: "添加记录",
            editCaption: "编辑记录",
            bSubmit: "提交",
            bCancel: "取消",
            bClose: "关闭",
            saveData: "数据已修改，是否保存？",
            bYes: "是",
            bNo: "否",
            bExit: "取消",
            msg: {
                required: "此字段必需",
                number: "请输入有效数字",
                minValue: "输值必须大于等于 ",
                maxValue: "输值必须小于等于 ",
                email: "这不是有效的e-mail地址",
                integer: "请输入有效整数",
                date: "请输入有效时间",
                url: "无效网址。前缀必须为 ('http://' 或 'https://')",
                nodefined: " 未定义！",
                novalue: " 需要返回值！",
                customarray: "自定义函数需要返回数组！",
                customfcheck: "必须有自定义函数!"
            }
        },
        view: {caption: "查看记录", bClose: "关闭"},
        del: {caption: "删除", msg: "删除所选记录？", bSubmit: "删除", bCancel: "取消"},
        nav: {
            edittext: "",
            edittitle: "编辑所选记录",
            addtext: "",
            addtitle: "添加新记录",
            deltext: "",
            deltitle: "删除所选记录",
            searchtext: "",
            searchtitle: "查找",
            refreshtext: "",
            refreshtitle: "刷新表格",
            alertcap: "注意",
            alerttext: "请选择记录",
            viewtext: "",
            viewtitle: "查看所选记录",
            savetext: "",
            savetitle: "保存记录",
            canceltext: "",
            canceltitle: "取消编辑记录",
            selectcaption: "操作..."
        },
        col: {caption: "选择列", bSubmit: "确定", bCancel: "取消"},
        errors: {
            errcap: "错误",
            nourl: "没有设置url",
            norecords: "没有需要处理的记录",
            model: "colNames 和 colModel 长度不等！"
        },
        formatter: {
            integer: {thousandsSeparator: ",", defaultValue: "0"},
            number: {
                decimalSeparator: ".",
                thousandsSeparator: ",",
                decimalPlaces: 2,
                defaultValue: "0.00"
            },
            currency: {
                decimalSeparator: ".",
                thousandsSeparator: ",",
                decimalPlaces: 2,
                prefix: "",
                suffix: "",
                defaultValue: "0.00"
            },
            date: {
                dayNames: ["日", "一", "二", "三", "四", "五", "六", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
                monthNames: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                AmPm: ["am", "pm", "上午", "下午"],
                S: function (a) {
                    return a < 11 || a > 13 ? ["st", "nd", "rd", "th"][Math.min((a - 1) % 10, 3)] : "th"
                },
                srcformat: "Y-m-d",
                newformat: "Y-m-d",
                parseRe: /[#%\\\/:_;.,\t\s-]/,
                masks: {
                    ISO8601Long: "Y-m-d H:i:s",
                    ISO8601Short: "Y-m-d",
                    ShortDate: "n/j/Y",
                    LongDate: "l, F d, Y",
                    FullDateTime: "l, F d, Y g:i:s A",
                    MonthDay: "F d",
                    ShortTime: "g:i A",
                    LongTime: "g:i:s A",
                    SortableDateTime: "Y-m-d\\TH:i:s",
                    UniversalSortableDateTime: "Y-m-d H:i:sO",
                    YearMonth: "F, Y"
                },
                reformatAfterEdit: !1,
                userLocalTime: !1
            },
            baseLinkUrl: "",
            showAction: "",
            target: "",
            checkbox: {disabled: !0},
            idName: "id"
        },
        colmenu: {
            sortasc: "升序排序",
            sortdesc: "降序排序",
            columns: "列",
            filter: "筛选",
            grouping: "分类",
            ungrouping: "取消分类",
            searchTitle: "查找:",
            freeze: "冻结",
            unfreeze: "取消冻结",
            reorder: "重新排序"
        }
    }
});