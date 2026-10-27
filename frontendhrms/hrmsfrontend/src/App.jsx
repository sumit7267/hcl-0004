import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import SampleCode from './component/SampleCode'
import UseFetchAPI from './component/UseFetchApi'
import Home from './component/Home'
import Login from './component/login'
import Registration from './component/Registration'
import Dashboard from './component/Dashboard'

function App() {
  return (
    <>
    <BrowserRouter>
    <Routes>
      <Route path='/' element={<Home/>}></Route>
      <Route path='/login' element={<Login/>}></Route>
      <Route path='/register' element={<Registration/>}></Route>
      <Route path='/dashboard' element={<Dashboard/>}></Route>
    </Routes>
    </BrowserRouter>
    </>
  )
}

export default App