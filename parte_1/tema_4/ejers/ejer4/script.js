function loadContent(){

    let content = document.getElementById('content');

    fetch('https://jsonplaceholder.typicode.com/posts/1')
    .then(response => response.json())
    .then(data => content.innerHTML = JSON.stringify(data, null, 2))
    .catch(error => console.log("Error: ",error));

}


