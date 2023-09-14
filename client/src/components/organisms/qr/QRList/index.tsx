import React, { useState, useEffect } from 'react';
import ListTotalText from 'components/atoms/common/ListTotalText';
import { IQRCode } from 'types/qr';
import { isAxiosError } from 'axios';
import toast from 'react-hot-toast';
import { findAllQRApi } from 'utils/apis/qr';
import { QRListWrapper } from './style';
import QRListItem from '../QRListItem';

function QRList() {
	const [QRListArray, setQRList] = useState<IQRCode[]>([]);

	const fetchQRList = async () => {
		try {
			const response = await findAllQRApi();

			if (response.status === 200) {
				setQRList(response.data.data.qrCodeResponses);
			}
		} catch (error) {
			if (isAxiosError(error)) {
				console.error(error);
				toast.error(error.response?.data.message);
			}
		}
	};

	useEffect(() => {
		fetchQRList();
	}, []);

	return (
		<QRListWrapper>
			<ListTotalText text="관리 중" totalCnt={QRListArray.length} />
			{QRListArray.length ? (
				QRListArray.map((el) => (
					<QRListItem
						key={el.qrCodeId}
						title={el.title}
						bankCode={el.bankCode}
						accountNumber={el.accountNumber}
						moneyUnit={el.amount}
					/>
				))
			) : (
				<div>등록된 QR이 없습니다.</div>
			)}
		</QRListWrapper>
	);
}

export default QRList;
