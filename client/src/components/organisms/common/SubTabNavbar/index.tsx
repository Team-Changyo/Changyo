import React from 'react';
import { ReactComponent as Left } from 'assets/icons/navigation/left.svg';
import NavbarLayout from 'layouts/common/NavbarLayout';
import { useNavigate } from 'react-router-dom';
import { SubTabNavbarContainer } from './style';

interface ISubTabNavbarProps {
	text: string;
}
/**
 * 뒤로가기 버튼 + 중앙타이틀이 적혀있는 상단 네비게이션바
 */
function SubTabNavbar({ text }: ISubTabNavbarProps) {
	const navigate = useNavigate();

	const handleLeftClick = () => {
		navigate(-1);
	};
	return (
		<NavbarLayout>
			<SubTabNavbarContainer>
				<button type="button" className="back-btn">
					<Left onClick={handleLeftClick} />
				</button>
				<h1>{text}</h1>
			</SubTabNavbarContainer>
		</NavbarLayout>
	);
}

export default SubTabNavbar;
