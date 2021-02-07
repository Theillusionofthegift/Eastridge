import logo from '../images/Illusion_Transparent.png';
import './app.css';
import 'bootstrap/dist/css/bootstrap.min.css'

function EntryPoint() {
  return (
    <div className="App">
      <header className="App-header">
        <a href='/Login'><img src={logo} className="illusion-logo" alt="logo" /></a>
        <p>
          Welcome to Illusion!
        </p>
      </header>
    </div>
  );
}

export default EntryPoint;
