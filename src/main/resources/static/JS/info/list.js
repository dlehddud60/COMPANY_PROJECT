$(function () {
var selectValue = $(".selectSearch").val()
    console.log("==================selectValue=================",selectValue);
});

function selectSearchFunc(obj) {
    var selectValue = $(obj).val();
    $("#searchInputBox").attr("name",selectValue)
}
