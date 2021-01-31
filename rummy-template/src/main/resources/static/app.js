var stompClient = null;

var matchId = null;

function connect() {

	value = document.querySelectorAll('body')[0].getAttribute('data'); // Übergabe des Parameters via Attribut im HTML-Tag
	                                                                   // ist mit Thymeleaf leichter zu realisieren.
    var socket = new SockJS('/rummy-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/update_rummy', function (message) {		// Bindet den Lambda-Ausdruck an den Socket.
			if (message.body != value)									// Falls sich der Wert auf Seiten des Servers (Inhalt der Nachricht), 
            	reload_rummy();											// geändert hat, wird die Seite neu geladen!
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

function reload_rummy() {											 
	disconnect();   												// Socket-Verbindung trennen und die Seite neu laden. 
	window.location.replace(window.location.href);					// Verwandelt den bisherigen Post-Request in einen Get-Request,
}																	// wodurch er auf Seiten des Servers identifiziert werden kann.

function drawOpen(){
    req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200){
            reload_rummy();
        }
    }
    req.open("POST", "http://localhost:8080/rummy/drawOpen");
    req.send();
}

function drawClosed(){
    req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200){
            reload_rummy();
        }
    }
    req.open("POST", "http://localhost:8080/rummy/drawClosed");
    req.send();
}

function discardCard(id){
    req = new XMLHttpRequest()
    req.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200){
            reload_rummy();
        }
    }
    req.open("POST", "http://localhost:8080/rummy/discard/" + id);
    req.send();
}

