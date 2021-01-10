function changeColor()
{
    var Head = document.getElementById("wrapper");
    Head.style.backgroundColor = "red";
}

function randomImage()
{
    fetch(`https://source.unsplash.com/random`).then((response) => {   
        let image = document.getElementById("pic");
        image.classList.add('item');
        image.innerHTML = `<img id="randomimage" src="${response.url}" alt="random"/>`     
        document.wrapper.appendChild(image);
      }) 
}