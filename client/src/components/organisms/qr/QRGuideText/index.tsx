import React from 'react';
import GuideText from 'components/atoms/common/GuideText';
import { QRGuideTextContainer } from './style';

interface IQRGuideTextProps {
	isDepositRequest: boolean;
	remainTime?: string;
}

function QRGuideText({ isDepositRequest, remainTime }: IQRGuideTextProps) {
	return (
		<QRGuideTextContainer>
			<GuideText text="고객에게 송금 QR을 보여주세요" />
			{isDepositRequest ? (
				<div />
			) : (
				<p className="remain-time">
					남은 시간 <span className="time-text">{remainTime}</span>
				</p>
			)}
		</QRGuideTextContainer>
	);
}

export default QRGuideText;
