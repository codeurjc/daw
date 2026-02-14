// REST API Functions
// ------------------

//Load items from server
async function getAllItems() {
    const response = await fetch('/items/');
    const items = await response.json();

    console.log('Items loaded: ' + JSON.stringify(items));
    return items;
}

//Create item in server
async function createItem(item) {
    const response = await fetch('/items/', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(item)
    });
    const newItem = await response.json();

    console.log("Item created: " + JSON.stringify(newItem));
    return newItem;
}

//Update item in server
async function updateItem(item) {
    const response = await fetch('/items/' + item.id, {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(item)
    });
    const updatedItem = await response.json();
    
    console.log("Updated item: " + JSON.stringify(updatedItem));
    return updatedItem;
}

//Delete item from server
async function deleteItem(itemId) {
    const response = await fetch('/items/' + itemId, {
        method: 'DELETE'
    });
    const deletedItem = await response.json();
    
    console.log("Deleted item " + itemId);
    return deletedItem;
}

//DOM functions
// ------------------

//Add Item to page
function addItemToPage(item) {

    var checked = '';
    var style = '';

    if (item.checked) {
        checked = 'checked';
        style = 'style="text-decoration:line-through"';
    }

    const html = '<div id="item-' + item.id + '">' +
        '<input type="checkbox" ' + checked + '>' +
        '<span ' + style + '>' + item.description + '</span>' +
        '<button>Delete</button>' +
        '</div>';

    const info = document.getElementById('info');
    info.insertAdjacentHTML('beforeend', html);
}

//Add all items to page
async function addAllItemsToPage() {
    
    const items = await getAllItems();
    
    for (var i = 0; i < items.length; i++) {
        addItemToPage(items[i]);
    }
}

function configureDeleteButtons() {

    const info = document.getElementById('info');

    info.addEventListener('click', async function (event) {

        const elem = event.target;
        if (elem.tagName === 'BUTTON') {

            const itemDiv = elem.parentElement;
            var itemId = itemDiv.id.split('-')[1];

            itemDiv.remove();
            await deleteItem(itemId);
        }
    });
}

function configureItemCheckboxes() {

    const info = document.getElementById('info');

    info.addEventListener('change', async function (event) {

        var elem = event.target;

        const itemDiv = elem.parentElement;
        var itemId = itemDiv.id.split('-')[1];

        var textSpan = itemDiv.querySelector('span');

        //Read item info from DOM elements
        var itemDescription = textSpan.textContent;
        var itemChecked = elem.checked;

        //Create updated item
        var updatedItem = {
            id: itemId,
            description: itemDescription,
            checked: itemChecked
        };

        //Update item in server
        await updateItem(updatedItem);

        //Update page when checked
        var style = itemChecked ? 'line-through' : 'none';
        textSpan.style.textDecoration = style;

    });
}

function configureAddItemButton() {

    document.getElementById('add-button').addEventListener('click', async function () {

        const input = document.getElementById('value-input');
        const value = input.value;
        input.value = '';

        const item = {
            description: value,
            checked: false
        };

        const itemWithId = await createItem(item);

        addItemToPage(itemWithId);
    });
}

document.addEventListener('DOMContentLoaded', async () => {

    configureDeleteButtons();
    configureItemCheckboxes();
    configureAddItemButton();

    await addAllItemsToPage();
})