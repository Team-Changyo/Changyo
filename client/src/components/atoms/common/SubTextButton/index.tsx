import React from 'react';
import { SubTextButtonWrapper } from './style';

function SubTextButton({ text, handleClick }: { text: string; handleClick: () => void }) {
	return (
		<SubTextButtonWrapper type="button" onClick={handleClick}>
			{text}
		</SubTextButtonWrapper>
	);
}

export default SubTextButton;
