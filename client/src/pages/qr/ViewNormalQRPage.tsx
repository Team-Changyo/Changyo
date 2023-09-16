import LargeMoneyText from 'components/atoms/common/LargeMoneyText';
// import SubTextButton from 'components/atoms/common/SubTextButton';
import React, { useEffect, useState } from 'react';
import Button from 'components/organisms/common/Button';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import QRGuideText from 'components/organisms/qr/QRGuideText';
import PageLayout from 'layouts/common/PageLayout';
import ViewQRPageLayout from 'layouts/page/qr/ViewQRPageLayout';
import RemittanceRequestInfo from 'components/organisms/qr/RemittanceRequestInfo';
import { INormalQRCode } from 'types/qr';
import { formatBankCode } from 'utils/common/formatBankCode';
import { useNavigate } from 'react-router-dom';
import toast from 'react-hot-toast';

function ViewNormalQRPage() {
	const navigate = useNavigate();
	const [accountInfo, setAccountInfo] = useState('');
	const [moneyUnit, setMoneyUnit] = useState(0);
	const [base64QrCode, setBase64QrCode] = useState('');
	const [timeRemaining, setTimeRemaining] = useState('3분 00초');

	useEffect(() => {
		const normalQRInfo = sessionStorage.getItem('normalQRInfo');
		const qrExpiredTime = sessionStorage.getItem('qrExpiredTime');

		if (normalQRInfo) {
			const parseData: INormalQRCode = JSON.parse(normalQRInfo);
			setAccountInfo(`${formatBankCode(parseData.bankCode)} ${parseData.accountNumber} (${parseData.memberName})`);
			setMoneyUnit(parseData.amount);
			setBase64QrCode(parseData.base64QrCode);
		} else {
			toast.error('QR코드가 만료되었습니다.');
			navigate('/qr');
		}

		// qrExpiredTime 이 null이 아니면,
		if (qrExpiredTime) {
			const intExpiredTime = parseInt(qrExpiredTime, 10);
			const timer = setInterval(() => {
				const gap = intExpiredTime - Date.now();

				if (gap <= 0) {
					clearInterval(timer);
					sessionStorage.removeItem('normalQRInfo');
					sessionStorage.removeItem('qrExpiredTime');
					toast.error('QR코드가 만료되었습니다.');
					navigate('/qr');
					return;
				}
				const min = Math.floor(gap / (60 * 1000));
				const sec = ((gap % (60 * 1000)) / 1000).toFixed(0);
				setTimeRemaining(`${min}분 ${sec}초`);
			}, 1000);
		}
	}, []);

	return (
		<PageLayout>
			<ViewQRPageLayout
				Navbar={<SubTabNavbar text="송금 QR 상세보기" type="close" closePath="/qr" />}
				RemittanceRequestInfo={<RemittanceRequestInfo accountInfo={accountInfo} isDepositRequest={false} />}
				MoneyUnit={<LargeMoneyText money={moneyUnit} />}
				QRImage={<img src={`data:image/jpeg;base64,${base64QrCode}`} alt="" />}
				GuideText={<QRGuideText isDepositRequest={false} remainTime={timeRemaining} />}
				QRShareBtn={<Button type="Primary" handleClick={() => {}} text="QR 공유하기" />}
				// LinkShareBtn={<SubTextButton text="QR 대신 링크로 공유" handleClick={() => {}} />}
				LinkShareBtn={<div />}
			/>
		</PageLayout>
	);
}

export default ViewNormalQRPage;
