import { Modal } from '@mui/material';
import React, { Dispatch, SetStateAction, useState } from 'react';

import Button from 'components/organisms/common/Button';
import CertCodeInput from 'components/atoms/auth/CertCodeInput';
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

	const confirmCert = () => {
		// TODO API 연결
		if (certCode === '1234') {
			alert('계좌 인증에 성공했습니다!');
			handleClose();
			setCertified(true);
		} else {
			alert('계좌 인증에 실패했습니다. 인증 번호를 다시 확인해주세요');
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
