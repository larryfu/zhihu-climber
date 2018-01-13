/**
 *
 * @author larryfu  all rights reserved
 * 职位类别、城市、行业选择器
 * 作者 付光增
 *
 *
 */

"use strict"

function DataProvider(data) {
    this.data = data;
}

DataProvider.prototype = (function () {
    function getGroup(data) {
        var group = {};
        for (var i in data)
            if (i.length === 2)
                group[i] = data[i];
        return group;
    }

    function getDivision(groupId, data) {
        var division = {};
        for (var i in data)
            if (i.length == 4 && i.substring(0, 2) == groupId)
                division[i] = data[i];
        return division;
    }

    function getTypes(divisionId, data) {
        var types = {};
        for (var i in data)
            if (i.length == 6 && i.substring(0, 4) == divisionId)
                types[i] = data[i];
        return types;
    }

    return {
        getGroup: function () {
            return getGroup(this.data);
        },
        getDivision: function (groupId) {
            return getDivision(groupId, this.data);
        },
        getTypes: function (divisionId) {
            return getTypes(divisionId, this.data);
        },
        constructor: DataProvider
    }
})();

function DataSelector(provider) {
    this.dataProvider = provider;
    this.inited = false;
    this.hideAction = [];
}

DataSelector.prototype = (function () {
    function generateTable(that) {
        var dataProvider = that.dataProvider;
        var groups = dataProvider.getGroup();
        for (var i in groups) {
            var subtypes = dataProvider.getDivision(i);
            var html = generateTr(i, groups[i], subtypes);
            var $html = $(html);
            $html.last().addClass("bottom");
            $html.appendTo(that.table.children('tbody'));
        }
        if (that.level2)
            initSelect(that);
    }

    function initSelect(that) {
        var single = that.single;

        that.selected.on("click", "label", function () {
            $(this).remove();
        });
        that.table.on("click", "td.division", function (e) {
            var num = $(this).attr("data-number");
            var name = $(this).text();
            if (single) {
                var selected = {};
                selected[num] = name;
                finishSelect([selected], that);
                return;
            }
            addSelected(num, name, that);
        });
    }

    function generateTr(key, value, subtypes) {
        var num = 0;
        for (var i in subtypes) num++;
        num = Math.ceil(num / 5);
        var html = "<tr> <td rowspan='" + num + "' data-number='" + key + "' class='group'>" + value + "</td> ";
        num = 0;
        for (var i in subtypes) {
            html += "<td  data-number='" + i + "' class='division'>" + subtypes[i] + "</td>";
            if ((++num) % 5 === 0)
                html += "</tr><tr>";
        }
        html += "</tr>";
        return html;
    }

    function initSublistContent(divisionNum, divisionName, that) {
        console.log("init initSublistContent")
        var dataProvider = that.dataProvider;
        var types = dataProvider.getTypes(divisionNum);
        var num = 0;
        for (var i in types)
            num++;

        var html = "";
        var lineNum = 2;
        if (num > 16)
            lineNum = 3;
        num = 0;
        for (var i in types) {
            var subHtml = "<td class='type' data-number='" + i + "'>" + types[i] + "</td>";
            if (num % lineNum == 0)
                subHtml = "<tr>" + subHtml;
            else if (num % lineNum == (lineNum - 1))
                subHtml = subHtml + "</tr>";
            html += subHtml;
            num++;
        }
        if (!(num % lineNum == (lineNum - 1))) html += "</tr>";
        that.subTable.children("tbody").empty().html(html);

    }

    function addSelected(number, name, that) {
        console.log(getSelected(that).length);
        if (getSelected(that).length >= 5) {
            alert("最多选择5个");
            return;
        }
        var html = "<label><input data-number='" + number + "' type='checkbox' checked='false'> <span style='color:#0297fd'>" + name + "</span></label>";
        var exists = isNumberExist(number, that);
        removeDuplicate(number, that);
        if (!exists)
            that.selected.append($(html));
    }

    function isNumberExist(number, that) {
        var exist = false;
        that.selected.children("label").each(function () {
            var num = $(this).children("input").attr("data-number");
            if (num === number)
                exist = true;
        });
        return exist;
    }

    function removeDuplicate(number, that) {
        that.selected.children("label").each(function () {
            var num = $(this).children("input").attr("data-number");
            console.log("number:" + number + ",num" + num);
            if (num === number || (num.length != number.length && num.substring(0, 4) === number.substring(0, 4)))
                $(this).remove();
        });
    }

    function getSelected(that) {
        var selected = [];
        that.selected.children("label").each(function () {
            var number = $(this).children("input").attr("data-number");
            var name = $(this).children("span").text();
            var obj = {};
            obj[number] = name;
            selected.push(obj);
        });
        return selected;
    }

    function finishSelect(selected, that) {
        that.wrapper.fadeOut(150);
        var names = [];
        var numbers = [];
        for (var i in selected)
            for (var j in selected[i]) {
                numbers.push(j);
                names.push(selected[i][j]);
            }
        setSelectedValue(names.join("、"), numbers.join(","), that);

        var hideAction = that.hideAction;
        for (var i in hideAction)
            if (hideAction[i] instanceof Function)
                hideAction[i]();
    }

    function initSublist(that) {

        console.log("init sublist");

        var single = that.single;

        that.subTable.on("click", "td", function () {
            if (single) {
                var selected = {};
                selected[$(this).attr("data-number")] = $(this).text();
                finishSelect([selected], that);
                return;
            }
            addSelected($(this).attr("data-number"), $(this).text(), that);
        });
        that.selected.on("click", "label", function () {
            $(this).remove();
        });


        that.table.on("click", "td.division", function (e) {
            var num = $(this).attr("data-number");
            var name = $(this).text();
            initSublistContent(num, name, that);
            that.subTable.css({
                top: e.clientY - 4,
                left: e.clientX - 4
            }).hover(function () {
                $(this).show();
            }, function () {
                $(this).hide();
            }).show();
        });
    }

    function getSelectedValue(that) {
        var selected = getSelected(that);
        var value = [];
        for (var i in selected)
            for (var j in selected[i])
                value.push(selected[i][j]);
        return value.join("、");
    }

    function setSelectedValue(name, number, that) {
        var inputId = that.inputId;
        var valueInput = $("#" + inputId);
        if (valueInput[0].tagName === "INPUT")
            valueInput.val(name);
        else
            valueInput.text(name);
        valueInput.attr("data-number", number);
    }

    function init(config, that) {

        that.level2 = config.level2;
        that.single = config.single;
        that.wrapperDiv = config.divId;
        that.inputId = config.inputId;

        var html = "<div class='selector-background'></div><div class='selector-content'><div class='hide-button'>取消</div> <div class='clear-button'>清空</div> <div class = 'selected-div'> 已选择类别: </div> <table class = 'selector-table' ><tbody> </tbody> </table> <table class = 'subtype-table' style = 'display:none'><tbody > </tbody> </table></div>";
        $("#" + that.wrapperDiv).empty().append($(html));
        initJqElements(that);
        that.hideButton.click(function () {
            if (that.single) {
                that.wrapper.fadeOut(150);
                return;
            }
            finishSelect(getSelected(that), that);
        });
        that.wrapper.find(".clear-button").click(function () {
            that.wrapper.fadeOut(150);
            setSelectedValue("不限", null,that);
            reloadData();
        });
    }

    function initJqElements(that) {
        that.wrapper = $("#" + that.wrapperDiv);
        that.wrapper.css({
            width: '100%',
            height: '100%'
        });
        that.hideButton = that.wrapper.find(".hide-button");
        that.table = that.wrapper.find(".selector-table");
        that.selected = that.wrapper.find(".selected-div");
        that.subTable = that.wrapper.find(".subtype-table");
    }

    return {
        init: function (config) {
            init(config, this);
            generateTable(this);
            if (!this.level2)
                initSublist(this);
            this.inited = true;
        },
        show: function (config) {
            if (!this.inited)
                this.init(config);
            $("#" + this.wrapperDiv).fadeIn(100);
            $('.selector-content').css("marginTop", -$('.selector-content').outerHeight() / 2 + "px");
        },
        onHide: function (callback) {
            if (callback instanceof Function)
                this.hideAction.push(callback);
        }
    }
})();

(function (global) {
    var industryData = new DataProvider(industry_data);
    var categoryData = new DataProvider(fun_a);

    function resumeOverflow() {
        $('html body').css('overflow', 'auto');
    }

    global.getFuntypeSelector = function () {
        var categorySelector = new DataSelector(categoryData);
        categorySelector.onHide(resumeOverflow);
        return categorySelector;
    }
    global.getIndustrySelector = function () {
        var industrySelector = new DataSelector(industryData);
        industrySelector.init = function (config) {
            config.level2 = true;
            DataSelector.prototype.init.call(this, config);
        };
        industrySelector.onHide(resumeOverflow);
        return industrySelector;
    }

})(window);
