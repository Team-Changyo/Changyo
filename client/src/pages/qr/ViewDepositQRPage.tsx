import React, { useEffect, useState } from 'react';
import LargeMoneyText from 'components/atoms/common/LargeMoneyText';
import SubTextButton from 'components/atoms/common/SubTextButton';
import Button from 'components/organisms/common/Button';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import QRGuideText from 'components/organisms/qr/QRGuideText';
import PageLayout from 'layouts/common/PageLayout';
import ViewQRPageLayout from 'layouts/page/qr/ViewQRPageLayout';
import RemittanceRequestInfo from 'components/organisms/qr/RemittanceRequestInfo';
import { useParams } from 'react-router-dom';
import { findQRApi } from 'utils/apis/qr';
import { formatBankCode } from 'utils/common/formatBankCode';
import { share } from 'utils/common/share';
import { IShareData } from 'types/deposit';

function ViewDepositQRPage() {
	const { qrCodeId } = useParams();
	const [title, setTitle] = useState('');
	const [accountInfo, setAccountInfo] = useState('');
	const [base64QrCode, setBase64QrCode] = useState('');
	const [moneyUnit, setMoneyUnit] = useState(0);
	const [shareData, setShareData] = useState<IShareData>({
		title: '송금을 요청합니다.',
		text: '송금을 요청합니다.',
		url: 'https://j9c205.p.ssafy.io/',
	});

	const fetchData = async () => {
		try {
			if (qrCodeId) {
				const response = await findQRApi(qrCodeId);
				console.log(response);

				if (response.status === 200) {
					const resObj = response.data.data;
					setTitle(resObj.title);
					setAccountInfo(`${formatBankCode(resObj.bankCode)} ${resObj.accountNumber} (${resObj.customerName})`);
					setBase64QrCode(resObj.base64QrCode);
					setMoneyUnit(resObj.amount);
					const blob = await (await fetch(resObj.base64QrCode)).blob();
					const file = new File([blob], 'changyo-qr.png', { type: blob.type });
					setShareData({
						title: `[${resObj.title}] 송금 요청`,
						url: resObj.url,
						text: `${resObj.customerName}님께 ${resObj.amount}을 송금해주세요!`,
						files: [file],
					});
				}
			}
		} catch (error) {
			console.error(error);
		}
	};
	useEffect(() => {
		fetchData();
	}, []);

	return (
		<PageLayout>
			<ViewQRPageLayout
				Navbar={<SubTabNavbar text="송금 QR 상세보기" type="close" closePath="/qr" />}
				RemittanceRequestInfo={
					<RemittanceRequestInfo accountInfo={accountInfo} isDepositRequest depositTitle={title} />
				}
				MoneyUnit={<LargeMoneyText money={moneyUnit} />}
				QRImage={<img src={`data:image/jpeg;base64,${base64QrCode}`} alt="" />}
				GuideText={<QRGuideText isDepositRequest />}
				QRShareBtn={
					<Button
						type="Primary"
						handleClick={async () => {
							await share(shareData);
						}}
						text="QR 공유하기"
					/>
				}
				LinkShareBtn={<SubTextButton text="QR 대신 링크로 공유" handleClick={() => {}} />}
			/>
		</PageLayout>
	);
}

export default ViewDepositQRPage;
