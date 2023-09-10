import React from 'react';
import { UnderlineButtonWrapper } from './style';

interface IUnderlineButtonProps {
	text: string;
	handleClick: () => void;
	type: 'Primary' | 'Normal' | 'Danger';
}
function UnderlineButton({ text, handleClick, type }: IUnderlineButtonProps) {
	return (
		<UnderlineButtonWrapper type="button" $type={type} onClick={handleClick}>
			{text}
		</UnderlineButtonWrapper>
	);
}

export default UnderlineButton;
