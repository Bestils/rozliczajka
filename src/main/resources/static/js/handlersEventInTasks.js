function changeStatusFinished(id)
{
  $.getJSON("/api/calculations/" + id)
    .done(function(calculation) {
      calculation.finished = !calculation.finished
      $.ajax("/api/calculations", {
        data: JSON.stringify(calculation),
        contentType: 'application/json',
        type: 'PATCH'
      });
    });
}

//For sortable items within and between lists
var items = [];
$(document).ready(function() {
  $("#sortableCategories > div").map(function () {
    items.push("#" + this.id);
    $(this).addClass("frameForcalculation");
  });
});

$(function() {
  $(items.toString()).sortable({
    connectWith: ".connectedSortable",
    cancel: "#titleCategory",
    stop: function(event, ui) {
      var calculationId = ui.item.attr("id");
      var newPositionInList = ui.item.index();
      var parent = ui.item.parent().attr('value');

      $.ajax({
        url: "/api/calculations/change",
        data: {
          "calculationId": calculationId,
          "newPositionInList": newPositionInList - 1,
          "newCategoryId" : parent
        },
        type: 'POST'
      });
    }
  }).disableSelection();
});
