import React from 'react';
import { SmallSubTextWrapper } from './style';

function SmallSubText({ text }: { text: string }) {
	return <SmallSubTextWrapper>{text}</SmallSubTextWrapper>;
}

export default SmallSubText;
