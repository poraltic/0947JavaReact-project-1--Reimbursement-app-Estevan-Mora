window.onload = function(){
	document.getElementById("b1").addEventListener("click", sortReimb);
	document.getElementById("b1").addEventListener("click", alert("clicked"));
}

function sortReimb(){
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			let sJSON = JSON.parse(xhr.responseText);
			console.log(sJSON);
			DOMManipulator(sJSON);
		}
	}
	xhr.open("POST", "http://localhost:8080/ProjectOne/displaytickets.json");
	xhr.send();
}

function DOMManipulator(sortJSON){
	html = "<table><tr>";
	
	for(var i = 0; i < sortJSON.length;i++){
		html += `<td>${sortJSON[i].reimbId}</td>`
		html += `<td>${sortJSON[i].amount}</td>`
		html += `<td>${sortJSON[i].author}</td>`
		html += `<td>${sortJSON[i].description}</td>`
		html += `<td>${sortJSON[i].reimbType}</td>`
		html += `<td>${sortJSON[i].resolved}</td>`
		html += `<td>${sortJSON[i].resolver}</td>`
		html += `<td>${sortJSON[i].statusType}</td>`
		html += `<td>${sortJSON[i].timestamp.month} ${sortJSON[i].timestamp.dayOfMonth} ${sortJSON[i].timestamp.year}</td>`
		html += '</tr><tr>'
	}
	html += '</tr></table>'
	document.getElementById('container').innerHTML = html;
	
}