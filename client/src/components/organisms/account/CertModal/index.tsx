import { Modal } from '@mui/material';
import React, { Dispatch, SetStateAction, useState } from 'react';

import Button from 'components/organisms/common/Button';
import CertCodeInput from 'components/atoms/auth/CertCodeInput';
import { toast } from 'react-hot-toast';
import { checkAuthAccountApi } from 'utils/apis/account';
import { isAxiosError } from 'axios';
import { CertModalContainer } from './style';

interface ICertModalProps {
	open: boolean;
	handleClose: () => void;
	bankName: string;
	accountNumber: string;
	setCertified: Dispatch<SetStateAction<boolean>>;
}

function CertModal(props: ICertModalProps) {
	const { open, handleClose, bankName, accountNumber, setCertified } = props;
	const [certCode, setCertCode] = useState('');

	// 계좌 인증확인
	const confirmCert = async () => {
		try {
			const body = {
				accountNumber,
				authenticationNumber: certCode,
			};

			const response = await checkAuthAccountApi(body);

			if (response.status === 204) {
				toast.success('계좌 인증에 성공했습니다!');
				handleClose();
				setCertified(true);
			}
		} catch (error) {
			console.error(error);
			if (isAxiosError(error)) {
				toast.error(error.response?.data.message);
			}
		}
	};

	const cancelCert = () => {
		if (window.confirm('계좌 인증을 취소하시겠어요?')) {
			handleClose();
		}
	};
	return (
		<Modal open={open} onClose={handleClose}>
			<CertModalContainer>
				<div className="content">
					<h2 className="first-message">
						<b>
							{bankName} {accountNumber}
						</b>
						<br />
						계좌로 1원을 보냈어요
					</h2>
					<h2 className="second-message">
						<b>입금자명</b>에 적힌
						<br />
						<b>4자리 인증번호</b>를 입력하세요
					</h2>
					<div className="cert-input">
						<CertCodeInput setCertCode={setCertCode} />
					</div>
				</div>
				<div className="btn-group">
					<Button handleClick={confirmCert} text="인증 완료" type="Primary" />
					<Button handleClick={cancelCert} text="취소" type="Normal" />
				</div>
			</CertModalContainer>
		</Modal>
	);
}

export default CertModal;
