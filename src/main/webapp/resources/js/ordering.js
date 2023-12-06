function addingToOrder() {
    // Get the container element
    var container = document.getElementById("addingWrap");

    // Get all buttons with class "foodButtons"
    var buttons = document.querySelectorAll('.foodButtons');

    // Loop through each button and clone/append it
    buttons.forEach(function (button) {
        var clonedButton = button.cloneNode(true);
        container.appendChild(clonedButton);
    });
}