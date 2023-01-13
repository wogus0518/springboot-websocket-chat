# WebSocket 으로 채팅 구현하기

단체 채팅방 구현, 주문이 들어오면 알림이 뜨는 기능을 구현하기 위해서 
실시간으로 서버와 클라이언트가 공유를 해야하는데 이를 위해서 WebSocket 을 이용

서버와 클라이언트는 1 : N 관계를 맺음
- 한 서버에 여러 클라이언트가 접속할 수 있으므로, 서버는 여러 클라이언트가 발송한 메세지를 받아서 처리해줄 Handler가 필요하다.
- TextWebSocketHandler를 상속받아 Handler를 작성

