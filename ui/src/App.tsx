import { useEffect, useRef, useState } from 'react';

function App() {
  const [messages, setMessages] = useState<string[]>([]);
  const [input, setInput] = useState('');
  const socketRef = useRef<WebSocket | null>(null);

  useEffect(() => {
    socketRef.current = new WebSocket("ws://localhost:8080/oracle-chat");

    socketRef.current.onopen = () => {
      console.log("Connected to Office Oracle");
    };

    socketRef.current.onmessage = (event) => {
      setMessages(prev => [...prev, `Oracle: ${event.data}`]);
    };

    return () => {
      socketRef.current?.close();
    };
  }, []);

  const sendMessage = () => {
    if (socketRef.current && socketRef.current.readyState === WebSocket.OPEN) {
      socketRef.current.send(input);
      setMessages(prev => [...prev, `You: ${input}`]);
      setInput('');
    } else {
      console.warn("Socket not open.");
    }
  };

  return (
    <div style={{ padding: '2rem', fontFamily: 'sans-serif' }}>
      <h1>Office Oracle</h1>
      <div>
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Ask the Oracle..."
          style={{ width: '300px', padding: '0.5rem' }}
        />
        <button onClick={sendMessage} style={{ marginLeft: '1rem' }}>
          Send
        </button>
      </div>
      <ul style={{ marginTop: '2rem' }}>
        {messages.map((msg, idx) => (
          <li key={idx}>{msg}</li>
        ))}
      </ul>
    </div>
  );
}

export default App;
