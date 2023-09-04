import React from 'react';
import { ButtonWrapper } from './style';

interface IButtonProps {
	handleClick: () => void;
	text: string;
	type: 'Normal' | 'Primary' | 'Danger';
}
function Button({ handleClick, text, type }: IButtonProps) {
	return (
		<ButtonWrapper $type={type}>
			<button type="button" onClick={handleClick}>
				{text}
			</button>
		</ButtonWrapper>
	);
}

export default Button;
