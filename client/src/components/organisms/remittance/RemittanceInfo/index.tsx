import React from 'react';
import { RemittanceInfoContainer } from './style';

interface IRemittanceInfoProps {
	isDepositRequest: boolean;
	depositTitle?: string;
}

function RemittanceInfo({ isDepositRequest, depositTitle }: IRemittanceInfoProps) {
	if (isDepositRequest)
		return (
			<RemittanceInfoContainer>
				<span className="deposit-title-info">
					<span className="title">{depositTitle}</span> 건
				</span>
				<h1 className="message">보증금을 송금합니다.</h1>
			</RemittanceInfoContainer>
		);
	return (
		<RemittanceInfoContainer>
			<h1 className="message">금액을 송금합니다</h1>
		</RemittanceInfoContainer>
	);
}

export default RemittanceInfo;
