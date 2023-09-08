import React from 'react';
import { GuideTextContainer } from './style';

interface IGuideTextProps {
	text: string;
}

function GuideText({ text }: IGuideTextProps) {
	return <GuideTextContainer>{text}</GuideTextContainer>;
}

export default GuideText;
