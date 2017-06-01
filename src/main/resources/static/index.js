$(document).ready(function() {

    // process the form
    $('form').submit(function(event) {

    var formData = {}
    formData["firstName"] = $("#firstName").val();
    formData["lastName"] = $("#lastName").val();
    formData["emailId"] = $("#email").val();
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        var address={};
        address["line1"]=$("#line1").val();
        address["line2"]=$("#line2").val();
        address["city"]=$("#city").val();
        address["postCode"]=$("#postCode").val();
        address["country"]=$("#country").val();
        formData["addresses"] = [];
        formData.addresses.push(address);
        console.log(formData);
        // process the form
         $("#addEmployee").prop("disabled", true);
       $.ajax({
            type        : "POST", // define the type of HTTP verb we want to use (POST for our form)
            url         : "employee", // the url where we want to POST
            data: JSON.stringify(formData),
            contentType: "application/json",
             dataType    : "json", // what type of data do we expect back from the server
             success: function (data) {

                 var json = "<h4>Employee Registration successful with Id " +  data.id +"</h4>";
                 $('#feedback').html(json);

                 console.log("SUCCESS : ", data);
                 $("#addEmployee").prop("disabled", false);
                 refreshEmployeeData();

             },
             error: function (e) {

                         var json = "<h4>Employee Registration Failed</h4>";
                         $('#feedback').html(json);

                         console.log("ERROR : ", e);
                         $("#addEmployee").prop("disabled", false);

             }
       });

        // stop the form from submitting the normal way and refreshing the page
        event.preventDefault();


    /*$.post("employee", JSON.stringify(formData),function(data, status){
                    alert("Data: " + data + "\nStatus: " + status);
                }
            );*/
    });

    function refreshEmployeeData(){
        $.ajax({
                type        : "GET", // define the type of HTTP verb we want to use (POST for our form)
                url         : "employee", // the url where we want to POST
                contentType: "application/json",
                 dataType    : "json", // what type of data do we expect back from the server
                 success: function (data) {
                       buildHtmlTable('#excelDataTable', data);
                 },
                 error: function (e) {
                        Console.log("Failed to load");
                 }
          });
    }


    // Builds the HTML Table out of data.
    function buildHtmlTable(selector, data) {
      var columns = addAllColumnHeaders(data, selector);

      for (var i = 0; i < data.length; i++) {
        var row$ = $('<tr/>');
        for (var colIndex = 0; colIndex < columns.length; colIndex++) {
          var cellValue = data[i][columns[colIndex]];
          if (cellValue == null) {cellValue = "";}
          else if($.isArray(cellValue)) {
            cellValue = cellValue.map(function(elem){
                return JSON.stringify(elem);
            }).join(",")
          }
          row$.append($('<td/>').html(cellValue));
        }
        $(selector).append(row$);
      }
    }

    // Adds a header row to the table and returns the set of columns.
    // Need to do union of keys from all records as some records may not contain
    // all records.
    function addAllColumnHeaders(data, selector) {
      var columnSet = [];
      var headerTr$ = $('<tr/>');

      for (var i = 0; i < data.length; i++) {
        var rowHash = data[i];
        for (var key in rowHash) {
          if ($.inArray(key, columnSet) == -1) {
            columnSet.push(key);
            headerTr$.append($('<th/>').html(key));
          }
        }
      }
      $(selector).empty();
      $(selector).append(headerTr$);

      return columnSet;
    }

    refreshEmployeeData();
});



