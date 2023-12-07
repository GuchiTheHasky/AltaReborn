
import { Outlet } from 'react-router-dom'
import { Header } from '../header/header.component'

export const Layout = () => {
  return (
    <div className='px-4'>
      <Header/>
      <Outlet/>
    </div>
  )
}
