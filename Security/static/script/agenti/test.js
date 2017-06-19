angular.module('simeCenterApp', [
  'ngWebSocket' // you may also use 'angular-websocket' if you prefer
])
//                          WebSocket works as well
.factory('MyData', function($websocket) {
  // Open a WebSocket connection
  var dataStream = $websocket('wss://localhost:8080/api/agent/all');

  var collection = [];

  dataStream.onMessage(function(message.data) {
    collection.push(JSON.parse(message.data));
  });

  var methods = {
    collection: collection,
    get: function() {
      dataStream.send(JSON.stringify({ action: 'get' }));
    }
  };

  return methods;
})