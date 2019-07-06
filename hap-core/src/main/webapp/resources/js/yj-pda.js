/**
 * Created by Rebs on 2017/6/13.
 */

/**
 * 定义报工过账字段
 */
var barcode;            //条码
var postingDate;        //过账日期
var orderno;            //生产订单
var operation;          //工序
var yeild;              //合格数量
var workScrap;          //工废数量
var rowScrap;           //料废数量
var classgrp;           //班组
var line;               //生产线
var modelNo;            //模号

var plant;              //工厂
var dispatch;           //报工单号
var dispatchLogicID;    //报工单流水号
var creationDate;       //创建日期、报工日期
var createdBy;          //创建人ID

var attr1;              //扩充字段1
var attr2;              //扩充字段2
var attr3;              //扩充字段3
var attr4;              //扩充字段4
var attr5;              //扩充字段5
var attr6;              //扩充字段6
var attr7;              //扩充字段7
var attr8;              //扩充字段8
var attr9;              //扩充字段9
var attr10;             //扩充字段10
var attr11;             //扩充字段11
var attr12;             //扩充字段12
var attr13;             //扩充字段13
var attr14;             //扩充字段14
var attr15;             //扩充字段15
var tm;
/**
 * 获取报工过账字段的值
 */

/**
 * 定义对照表字段
 */
var wipZpgdbar;
var wipAufnr;
var wipAuart;
var wipArbpl;
var wipMatnr;
var newAufnr;
var newZpgdbar;

var pd_sum;
var arbpl;
var arbplDesc;
var matnr;
var maktx;
var mode;
var mdnum;
function getDispatchValues() {
    tm = new Date().format("yyyy-MM-dd hh:mm:ss");
    var bg = getTodayDate() + " 23:59:59" ;
    var ed = getDateAdd(1) + " 07:20:00";
    barcode = document.getElementById("barcode").value;
    // if(document.getElementById("postingDate").selectedIndex == "")
    // {
    //
    //     postingDate = document.getElementById("postingDate").options[0].value;
    // }else{
    //     postingDate = document.getElementById("postingDate").options[document.getElementById("postingDate").selectedIndex].value;
    // }

    orderno = document.getElementById("orderno").value;
    operation = document.getElementById("operation").value;
    yeild = document.getElementById("yeild").value;
    workScrap = document.getElementById("workScrap").value;
    rowScrap = document.getElementById("rowScrap").value;
/*    classgrp = document.getElementById("classgrp").value;*/
    classgrp = document.getElementById("classgrp").options[document.getElementById("classgrp").selectedIndex].value;
    line = document.getElementById("line").value;
    modelNo = document.getElementById("modelNo").value;
    plant = barcode.substring(0,4);
    dispatch = barcode.substring(0,barcode.length-4);
    dispatchLogicID = barcode.substring(barcode.length-8,barcode.length-4);
    if(yeild == "" || yeild == null){
        yeild = "0";
    }
    if(workScrap == "" || workScrap == null){
        workScrap = "0";
    }
    if(rowScrap == "" || rowScrap == null){
        rowScrap = "0";
    }

    attr1 = document.getElementById("attr1").value;
    attr2 = document.getElementById("attr2").value;
    attr3 = document.getElementById("attr3").value;
    attr4 = document.getElementById("attr4").value;
    attr5 = document.getElementById("attr5").value;
    /**
     * add by 张滔 2017-12-01
     * for 添加逻辑 针对订单类型YZ01 YZ04 YZ06
     */
    if((orderno >= "1000000000" && orderno <= "2999999999") || (orderno >= "9400000000" && orderno <= "9499999999")){
            if(document.getElementById("attr6").selectedIndex == "" || document.getElementById("attr6").selectedIndex < "0")
            {
                attr6 =  document.getElementById("attr6").options[0].value;

            }else{
                attr6 =  document.getElementById("attr6").options[document.getElementById("attr6").selectedIndex].value;
            }

            if( tm >= bg && tm <= ed){//晚班0点-早上八点 生产日期为前一日日期
                if( document.getElementById("attr6").selectedIndex == "0" ) {
                    attr6 =  document.getElementById("attr6").options[1].value;
                }

            }else{
                if( document.getElementById("attr6").selectedIndex == "0" ){
                    attr6 =  document.getElementById("attr6").options[0].value;
                }

            }

            document.getElementById("attr6").value = attr6;

        if(operation =="0010"){
            if(document.getElementById("attr8").value == " " || document.getElementById("attr8").value == "" ){
                attr8 = " "
            }else{
                attr8 = document.getElementById("attr8").options[document.getElementById("attr8").selectedIndex].value;
            }

            if(attr8 == " " || attr8 == "" || attr8 == null ){

                attr7 =  attr6.substring(2,attr6.length-6) + attr6.substring(5,attr6.length-3) + attr6.substring(8,attr6.length) + classgrp.toLowerCase();
            }else{
                attr7 =  attr8.substring(2,attr8.length-6) + attr8.substring(5,attr8.length-3) + attr8.substring(8,attr8.length) + classgrp.toLowerCase();
            }

        }else{
            attr8 = " "
            attr7 = document.getElementById("attr7").value;
        }

    }else{
        attr6 = document.getElementById("attr6").value;
        attr7 = document.getElementById("attr7").value;
        attr8 = document.getElementById("attr8").value;
    }

    attr9 = document.getElementById("attr9").value;
    attr10 = document.getElementById("attr10").value;
    attr11 = document.getElementById("attr11").value;
    attr12 = document.getElementById("attr12").value;
    attr13 = document.getElementById("attr13").value;
    attr14 = document.getElementById("attr14").value;
    attr15 = document.getElementById("attr15").value;
}

/**
 * 清空报工过账字段的值
 */
function delDispatchValues() {
    document.getElementById("barcode").value = "";

    document.getElementById("orderno").value = "";
    document.getElementById("operation").value = "";
    document.getElementById("yeild").value = "";
    document.getElementById("workScrap").value = "";
    document.getElementById("rowScrap").value = "";
/*    document.getElementById("classgrp").value = "";*/
    document.getElementById("line").value = "";
    document.getElementById("modelNo").value = "";

    document.getElementById("attr1").value = "";
    document.getElementById("attr2").value = "";
    document.getElementById("attr3").value = "";
    document.getElementById("attr4").value = "";
    document.getElementById("attr5").value = "";
    document.getElementById("attr6").value = "";
    document.getElementById("attr7").value = "";
    document.getElementById("attr8").value = "";
    document.getElementById("attr9").value = "";
    document.getElementById("attr10").value = "";
    document.getElementById("attr11").value = "";
    document.getElementById("attr12").value = "";
    document.getElementById("attr13").value = "";
    document.getElementById("attr14").value = "";
    document.getElementById("attr15").value = "";

}

/**
 * 检查报工过账必输字段 及字段格式
 */
function checkDispatchValues() {
    orderno = document.getElementById("orderno").value;
    var a = 0;
    if(barcode == " " || barcode == null){
        return "请扫码";
    }
    if(yeild == " " || yeild == null || yeild == "0"){
        a = a + 1;
    }
    if(workScrap == " " || workScrap == null || workScrap == "0"){
        a = a + 1;
    }
    if(rowScrap == " " || rowScrap == null || rowScrap == "0"){
        a = a + 1;
    }
    if( a == 3){
        return "请输入数量";
    }

    if ((orderno >= "1000000000" && orderno <= "2999999999") || (orderno >= "3000000000" && orderno <= "4999999999") || (orderno >= "9400000000" && orderno <= "9499999999")){
        /**
         * add by 张滔 2017年11月30日
         * for 生产线输入校验(只针对订单类型QP01 YZ01 YZ04 YZ06)
         */
        if (line == " " || line == null || line == "0" || line.length == 0){
            return "生产线必输"
        }
        /**
         *  add by 张滔 2017年11月30日
         *  for 模号必输校验(只针对订单类型QP01 YZ01 YZ04 YZ06)
         */
        if (modelNo == " " || modelNo == null || modelNo == "0" || modelNo.length == 0){
            return "模号必输"
        }
    }
    /**
        if  (attr9.length == 0 || attr9.length == 4)
        {
            if (attr9.length != 0)
            {
                var regpos = /\d{4}/g

                if( regpos.test(attr9)) {

                }else{
                    return "原因代码错误"
                }
            }

        }else{
                return "原因代码错误"
        }
     **/
    return null;
}
/**
 * 校验质量原因代码
 */
function isNumber(val) {
    var regpos = /\d{4}/g

    if( regpos.test(regpos)) {
        return null
    }else{
        return "原因代码错误"
    }
}

/**
 * 定义新旧对照表数据传递JSON
 */
var bartobar;
function setBartobar() {

    bartobar={
        "wipZpgdbar":wipZpgdbar,
        "wipAufnr":wipAufnr,
        "wipAuart":wipAuart,
        "wipArbpl":wipArbpl,
        "wipMatnr":wipMatnr,
        "newAufnr":newAufnr,
        "newZpgdbar":newZpgdbar
    };
}
/**
 * 定义报工数据传递json
 */
var dispatchData;
var dispatchDataMin;

/**
 * 设置报工数据传递json
 */
function setDispatchData() {
    dispatchDataMin={
        "a":barcode,
        "b":postingDate,
        "c":orderno,
        "d":operation,
        "e":yeild,
        "f":workScrap,
        "g":rowScrap,
        "h":classgrp,
        "i":line,
        "j":modelNo,
        "k":plant,
        "l":dispatch,
        "m":dispatchLogicID,
        "n":createdBy,
        "1":attr1,
        "2":attr2,
        "3":attr3,
        "4":attr4,
        "5":attr5,
        "6":attr6,
        "7":attr7,
        "8":attr8,
        "9":attr9,
        "10":attr10,
        "11":attr11,
        "12":attr12,
        "13":attr13,
        "14":attr14,
        "15":attr15
    };
    dispatchData={
        "barcode":barcode,
        "postingDate":postingDate,
        "orderno":orderno,
        "operation":operation,
        "yeild":yeild,
        "workScrap":workScrap,
        "rowScrap":rowScrap,
        "classgrp":classgrp,
        "line":line,
        "modelNo":modelNo,
        "plant":plant,
        "dispatch":dispatch,
        "dispatchLogicID":dispatchLogicID,
        "createdBy":createdBy,
        "attr1":attr1,
        "attr2":attr2,
        "attr3":attr3,
        "attr4":attr4,
        "attr5":attr5,
        "attr6":attr6,
        "attr7":attr7,
        "attr8":attr8,
        "attr9":attr9,
        "attr10":attr10,
        "attr11":attr11,
        "attr12":attr12,
        "attr13":attr13,
        "attr14":attr14,
        "attr15":attr15
    };
}

/**
 * using for formatting the Date
 * @param fmt
 * @returns {*}
 */
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

/**
 * using for setting cookie
 * @param name
 * @param value
 */
function setCookie(name,value)
{
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ value + ";expires=" + exp.toGMTString()+"domain=1;path=/";
}

/**
 * using for getting cookie
 * @param name
 * @returns {*}
 */
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return arr[2];
    else
        return null;
}

/**
 * using for deleting cookie
 * @param name
 * @returns {*}
 */
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString()+"domain=1;path=/";
}

/**
 * using for getting the date of the same day
 * @returns {*}
 */
function getTodayDate() {
    return new Date().format("yyyy-MM-dd");
}

/**
 * using for getting the date of yesterday
 * @returns {*}
 */
function getYesterdayDate() {
    var yesterday = new Date();
    yesterday.setDate(yesterday.getDate()-1);
    return yesterday.format("yyyy-MM-dd");
}

function getDate(i){
    var tmpday = new Date();
    tmpday.setDate(tmpday.getDate()-i);
    return tmpday.format("yyyy-MM-dd");
}

function getDateAdd(i){
    var tom = new Date();
    tom.setDate(tom.getDate()+i);
    return tom.format("yyyy-MM-dd");
}

/**
 * using for appending the mask Dialog
 */
function appendMaskDialog() {
    var oMaskD=document.createElement("div");
    oMaskD.id="mask";
    oMaskD.style.height="320px";
    oMaskD.style.width="240px";
    document.body.appendChild(oMaskD);
}

/**
 * using for removing the mask Dialog
 */
function removeMaskDialog() {
    var oMaskD=document.getElementById("mask");
    document.body.removeChild(oMaskD);
}

/**
 * using for appending the mask Dialog
 */
function appendMask2Dialog() {
    var oMaskD2=document.getElementById("mask2");
    oMaskD2.style.display = "";
}

/**
 * using for removing the mask Dialog
 */
function removeMask2Dialog() {
    var oMaskD2=document.getElementById("mask2");
    oMaskD2.style.display = "none";
}