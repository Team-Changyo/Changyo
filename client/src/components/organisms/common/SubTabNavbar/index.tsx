import React from 'react';
import { ReactComponent as Left } from 'assets/icons/navigation/left.svg';
import { ReactComponent as Right } from 'assets/icons/navigation/right.svg';
import NavbarLayout from 'layouts/common/NavbarLayout';
import { useNavigate } from 'react-router-dom';
import { SubTabNavbarContainer } from './style';

interface ISubTabNavbarProps {
	text: string;
	type: 'back' | 'close';
	closePath?: string;
}
/**
 * 뒤로가기 버튼 + 중앙타이틀이 적혀있는 상단 네비게이션바
 */
function SubTabNavbar({ text, type, closePath }: ISubTabNavbarProps) {
	const navigate = useNavigate();

	const handleLeftClick = () => {
		if (type === 'back') navigate(-1);
		else navigate(closePath as string);
	};

	return (
		<NavbarLayout>
			<SubTabNavbarContainer>
				{type === 'back' ? (
					<button type="button" className="back-btn">
						<Left onClick={handleLeftClick} />
					</button>
				) : (
					<button type="button" className="close-btn">
						<Right onClick={handleLeftClick} />
					</button>
				)}

				<h1>{text}</h1>
			</SubTabNavbarContainer>
		</NavbarLayout>
	);
}

export default SubTabNavbar;
