import logo from './Illusion_Transparent..png';
import './App.css';
import TitleBox from './component/TitleBox'

function App() {
  return (
    <div className="App">
      <TitleBox />
      <header className="App-header">
        <img src={logo} className="illusion-logo" alt="logo" />
        <p>
          Welcome to Illusion!
        </p>
      </header>
    </div>
  );
}

export default App;
