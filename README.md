# WebSocket 으로 채팅 구현하기(1)

단체 채팅방 구현, 주문이 들어오면 알림이 뜨는 기능을 구현하기 위해서 
실시간으로 서버와 클라이언트가 공유를 해야하는데 이를 위해서 WebSocket 을 이용

## WebSocketHandler
- 서버와 클라이언트는 1 : N 관계로 한 서버에 여러 클라이언트가 접속할 수 있다.
- 따라서, 서버는 여러 클라이언트가 발송한 메세지를 받아서 처리해줄 Handler가 필요하다.
- `TextWebSocketHandler`를 상속받아 Handler를 작성하자.
- 클라이언트로부터 메시지를 받으면 Message 객체로 변환
- Message 객체에 담긴 roomId로 발송 대상 채팅방 번호를 조회
- 해당 채팅방에 입장해 있는 모든 클라이언트들에게 타입에 맞는 메시지를 전송

## WebSocketConfig
- 위에서 작성한 Handler를 이용해서 WebSocket을 활성화하기 위한 Config 파일을 작성한다.
- `WebSocketConfigurer`을 상속받아 Config를 작성한다.
- `@EnableWebSocket`을 선언하여 WebSocket 활성화
- Handler 추가 + cors 설정

## Model - Message, MsgRoom
### Message
- 채팅 메세지를 주고받기 위한 DTO
- 메세지타입, 채팅방 번호, 보내는 사람, 메세지 내용
### MsgRoom
- 채팅방을 위한 DTO
- 채팅방은 현재 방에 입장한 클라이언트의 Session 정보를 가져야 한다.
- 입장시 -> session 정보 리스트에 입장한 클라이언트의 session을 추가
- 채팅방에 메시지가 도착했을 시 -> 채팅방이 가지고 있는 모든 session에 메세지를 발송
- 채팅방에는 입장 / 통신 기능이 있으므로 handleAction을 통해 분기 처리

## Service - MsgService
- 채팅방 생성 -> `createRoom()`
- 채팅방 조회 -> `findAllRoom()`, `findById()`
- 메시지 발송 : 지정한  WebSocket 세션에 메시지 발송 -> `sendMessage()`

## Controller - MsgController
- POST "/chat" -> 채팅방 생성
- GET "/chat" -> 채팅방 전체 조회

## 실행 확인
### 채팅방 생성
![image](https://user-images.githubusercontent.com/63176744/213842097-aa663732-e3c9-45e5-8d15-b24efc7ab2c8.png)
### 채팅방 입장
![image](https://user-images.githubusercontent.com/63176744/213842704-323acbd3-a96f-49fb-b282-d1f995a251fc.png)

# WebSocket 으로 채팅 구현하기(2)
메시지 타입을 지정하고, 타입별로 분기 처리를 해주어야 했다.
STOMP를 적용해서 이를 변경해보자.

STOMP : Simple Text Oriented Messaging Protocol

메세지 전송을 효율적으로 하기 위한 프로토콜, 기본적으로 PUB/SUB 구조로 되어있다.
-> 메시지를 전송하고 / 받아서 처리하는 부분이 확실하게 구조로 정해져있다.

## WebSocketConfig 수정
- STOMP 사용을 위해 `@EnableWebSocketMessageBrocker` 선언하고 `WebSocketMessageBrokerConfigurer`를 상속받아서 구현한다.
- 클라이언트와의 연결은 SockJs()로 한다.

## MsgRoom DTO 수정
- 구독자 관리가 알아서 되므로 웹소켓 세션 관리가 필요 없어진다.
- 발송의 구현도 알아서 해결이 되므로 일일이 클라이언트에게 메시지를 발송하는 구현이 필요 없어진다.