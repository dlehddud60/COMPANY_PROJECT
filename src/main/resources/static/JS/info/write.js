$(function () {
    $(".updateBtn").on("click", function () {
        $("#frm").submit();
    });
    let companyValue = $("input[name=companyResult]:checked").val();
    $("input[name=companyName]").attr("disabled",true)
    $("input[name=companyAddress]").attr("disabled",true)
    $("select[name=companyId]").attr("disabled",false)

    $("input:radio[name=companyResult]").on("click",function () {
        let companyVal = $("input[name=companyResult]:checked").val();
        if(companyVal == 'Y') {
            $("input[name=companyName]").attr("disabled",true)
            $("input[name=companyAddress]").attr("disabled",true)
            $("select[name=companyId]").attr("disabled",false)

        }else {
            $("select[name=companyId]").attr("disabled",true)
            $("input[name=companyName]").attr("disabled",false)
            $("input[name=companyAddress]").attr("disabled",false)
        }
    });
});
