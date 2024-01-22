import {Outlet} from 'react-router-dom'
import {Header} from '../header/header.component'

export const Layout = () => {
    return (
        <div className='px-6'>
            <Header/>
            <div className={'py-8 max-w-[1024px] mx-auto'}>
                <Outlet/>
            </div>
        </div>
    )
}
