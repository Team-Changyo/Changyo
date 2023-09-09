import React from 'react';
import 'react-confirm-alert/src/react-confirm-alert.css';
import { confirmAlert } from 'react-confirm-alert';
import Button from 'components/organisms/common/Button';
import UnderLineInput from 'components/atoms/common/UnderLineInput';
import { DepositRetrunModalWrapper } from './style';

interface IDepositReturnModalProps {
	depositorName: string;
	params: object;
	onAction: (params: object) => void;
}

function DepositReturnModal(props: IDepositReturnModalProps) {
	const { depositorName, params, onAction } = props;
	// 제목, 내용, 버튼 내용, 인자, confirm 함수, close 함수
	return confirmAlert({
		customUI: ({ onClose }) => (
			<DepositRetrunModalWrapper>
				<div className="deposit-return-modal-container">
					<div className="content">
						<h2>보증금을 반환합니다</h2>
						<h2>
							<span>{depositorName}</span>님에게
							<br />
							얼마를 반환할까요?
						</h2>
						<UnderLineInput width="150px" placeholder="" unitText="원" />
					</div>

					<div className="done-btn">
						<Button
							handleClick={() => {
								onAction(params);
								onClose();
							}}
							text="반환완료"
							type="Primary"
						/>
						<Button
							handleClick={() => {
								onAction(params);
								onClose();
							}}
							text="반환취소"
							type="Normal"
						/>
					</div>
				</div>
			</DepositRetrunModalWrapper>
		),
	});
}

export default DepositReturnModal;

/*

<DepositRetrunModalContainer>
				<div className="popup-overlay">
					<h1>{title}</h1>
					<p>{desc}</p>
					<div className="btn-group">
						<button type="button" onClick={onClose} className="btn-cancel">
							<span className="txt-wrap">취소</span>
						</button>
						<button
							type="button"
							onClick={() => {
								onAction(params);
								onClose();
							}}
							className="btn-confirm"
						>
							<span className="txt-wrap">{btnTitle}</span>
						</button>
					</div>
				</div>
			</DepositRetrunModalContainer>
*/
