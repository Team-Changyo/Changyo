import React from 'react';
import { RemittanceRequestInfoContainer } from './style';

interface IRemittanceRequestInfoProps {
	accountInfo: string;
	isDepositRequest: boolean;
	depositTitle?: string;
}

function RemittanceRequestInfo({ isDepositRequest, accountInfo, depositTitle }: IRemittanceRequestInfoProps) {
	if (isDepositRequest)
		return (
			<RemittanceRequestInfoContainer>
				<span className="deposit-title-info">
					<span className="title">{depositTitle}</span> 건
				</span>
				<h1 className="message">보증금 송금을 요청합니다</h1>
				<span className="account-info">{accountInfo}</span>
			</RemittanceRequestInfoContainer>
		);
	return (
		<RemittanceRequestInfoContainer>
			<h1 className="message">송금을 요청합니다</h1>
			<span className="account-info">{accountInfo}</span>
		</RemittanceRequestInfoContainer>
	);
}

export default RemittanceRequestInfo;
