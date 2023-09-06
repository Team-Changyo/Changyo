import React from 'react';
import { OptionTitleTextWrapper } from './style';

function OptionTitleText({ text }: { text: string }) {
	return <OptionTitleTextWrapper>{text}</OptionTitleTextWrapper>;
}

export default OptionTitleText;
