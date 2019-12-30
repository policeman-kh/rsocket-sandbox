const {
  RSocketClient,
  JsonSerializer,
  IdentitySerializer
} = require('rsocket-core');
const RSocketWebSocketClient = require('rsocket-websocket-client').default;
var client = undefined;

function main() {
client = new RSocketClient({
  serializers: {
    data: JsonSerializer,
    metadata: IdentitySerializer
  },
  setup: {
    // ms btw sending keepalive to server
    keepAlive: 60000,
    // ms timeout if no keepalive response
    lifetime: 180000,
    // format of `data`
    dataMimeType: 'application/json',
    // format of `metadata`
    metadataMimeType: 'message/x.rsocket.routing.v0',
  },
  transport: new RSocketWebSocketClient({
    url: 'ws://localhost:8080/rsocket'
  }),
});

client.connect().subscribe({
  onComplete: socket => {
  // socket provides the rsocket interactions fire/forget, request/response,
  // request/stream, etc as well as methods to close the socket.
  socket.requestStream({
  //socket.requestResponse({
    data: {
      'message': 'aiueo'
    },
    metadata: String.fromCharCode('getFlux'.length) + 'getFlux',
  }).subscribe({
    onComplete: () => console.log('complete'),
    onError: error => {
      console.log(error);
    },
    onNext: payload => {
      console.log(payload.data);
    },
    onSubscribe: subscription => {
      subscription.request(2147483647);
    },
  });
},
onError: error => {
  console.log(error);
},
onSubscribe: cancel =>
  {
    /* call cancel() to abort */
    console.log(cancel);
  }
});
}
document.addEventListener('DOMContentLoaded', main);
