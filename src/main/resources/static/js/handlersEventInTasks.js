function changeStatusFinished(id)
{
  $.getJSON("/api/tasks/" + id)
    .done(function(task) {
      task.finished = !task.finished
      $.ajax("/api/tasks", {
        data: JSON.stringify(task),
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
    $(this).addClass("frameForTask");
  });
});

$(function() {
  $(items.toString()).sortable({
    connectWith: ".connectedSortable",
    cancel: "#titleCategory",
    stop: function(event, ui) {
      var taskId = ui.item.attr("id");
      var newPositionInList = ui.item.index();
      var parent = ui.item.parent().attr('value');

      $.ajax({
        url: "/api/tasks/change",
        data: {
          "taskId": taskId,
          "newPositionInList": newPositionInList - 1,
          "newCategoryId" : parent
        },
        type: 'POST'
      });
    }
  }).disableSelection();
});
