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


