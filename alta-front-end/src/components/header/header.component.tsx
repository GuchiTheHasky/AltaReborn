import logo from '../../assets/logo.svg';
import {Link} from "react-router-dom";

export const Header = () => {
    return (
        <div>
            <Link to={'/'} className='w-[220px] h-[112px] block'>
                <img src={logo} alt='logo' className='w-[220px] h-[112px]'/>
            </Link>
        </div>
    )
}
