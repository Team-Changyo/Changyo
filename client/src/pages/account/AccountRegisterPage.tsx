import React, { useEffect, useState } from 'react';
import OptionTitleText from 'components/atoms/common/OptionTitleText';
import UnderLineInput from 'components/atoms/common/UnderLineInput';
import Button from 'components/organisms/common/Button';
import CustomStringSelect from 'components/organisms/common/CustomSelect';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import { BANKNAMES_BY_BANKCODES } from 'constants/bankCode';
import PageLayout from 'layouts/common/PageLayout';
import AccountRegisterPageLayout from 'layouts/page/account/AccountRegisterPageLayout';
import CheckText from 'components/atoms/common/CheckText';
import CertModal from 'components/organisms/account/CertModal';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-hot-toast';
import { authRequestAccountApi, registerAccountApi } from 'utils/apis/account';
import { isAxiosError } from 'axios';

function AccountRegisterPage() {
	const [isDone, setIsDone] = useState(false);
	const [bankCode, setBankCode] = useState('');
	const [accountNumber, setAccountNumber] = useState('');
	const [alias, setAlias] = useState('');
	const [isMainAccount, setIsMainAccount] = useState(false);
	const [modalOpen, setModalOpen] = useState(false);
	const [certified, setCertified] = useState(false);
	const navigate = useNavigate();

	const confirmRegister = async () => {
		if (isDone) {
			try {
				const body = {
					bankCode,
					accountNumber,
					title: alias,
					mainAccount: isMainAccount,
				};

				const response = await registerAccountApi(body);
				console.log(response);

				if (response.status === 201) {
					toast.success('계좌 등록 성공 !');
					navigate('/account');
				}
			} catch (error) {
				console.error(error);
				if (isAxiosError(error)) {
					toast.error(error.response?.data.message);
				}
			}
		}
	};

	const sendCertRequest = async () => {
		if (!bankCode) {
			toast.error('은행을 선택해주세요');
		} else if (!accountNumber) {
			toast.error('계좌번호를 입력해주세요');
		} else if (window.confirm('1원 이체 인증을 요청하시겠습니까?')) {
			// TODO : 계좌가 유효한지 검증로직 추가 (해커톤때는 안할듯)
			// 유효하다면 모달 오픈
			const body = {
				bankCode,
				accountNumber,
			};

			const response = await authRequestAccountApi(body);
			if (response.status === 200) {
				console.log(response.data);
			}
			setModalOpen(true);
		}
	};

	const getBankName = () => {
		if (bankCode && bankCode !== '000') return BANKNAMES_BY_BANKCODES.find((el) => el.key === bankCode)!.value;
		return '';
	};

	useEffect(() => {
		if (certified && alias) {
			setIsDone(true);
		} else {
			setIsDone(false);
		}
	}, [certified, alias]);
	return (
		<PageLayout>
			<AccountRegisterPageLayout
				Navbar={<SubTabNavbar text="계좌 추가하기" type="back" />}
				SelectBankTitle={<OptionTitleText text="은행 선택 " />}
				SelectBank={
					<CustomStringSelect
						value={bankCode}
						setValue={setBankCode}
						SELECT_OPTIONS={BANKNAMES_BY_BANKCODES}
						disabled={!!certified}
					/>
				}
				InputAccountNumberTitle={<OptionTitleText text="계좌번호를 입력하세요" />}
				InputAccountNumber={
					<UnderLineInput
						placeholder=""
						type="string"
						width="250px"
						value={accountNumber}
						setValue={setAccountNumber}
						disabled={!!certified}
					/>
				}
				InputAccountAliasTitle={<OptionTitleText text="계좌의 별명을 입력하세요" />}
				InputAccountAlias={
					<UnderLineInput placeholder="" type="string" width="250px" value={alias} setValue={setAlias} />
				}
				CertAccountBtn={
					certified ? (
						<Button handleClick={() => {}} text="계좌 인증 완료" type="Normal" />
					) : (
						<Button
							handleClick={sendCertRequest}
							text="1원 이체 인증 요청"
							type={bankCode && accountNumber ? 'Primary' : 'Normal'}
						/>
					)
				}
				CheckIsMainAccount={
					<CheckText value={isMainAccount} setValue={setIsMainAccount} text="해당 계좌를 주 계좌로 사용할래요" />
				}
				AccountRegisterBtn={
					<Button handleClick={confirmRegister} text="계좌 등록 완료" type={isDone ? 'Primary' : 'Normal'} />
				}
				CertModal={
					<CertModal
						open={modalOpen}
						handleClose={() => setModalOpen(false)}
						bankName={getBankName()}
						accountNumber={accountNumber}
						setCertified={setCertified}
					/>
				}
			/>
		</PageLayout>
	);
}

export default AccountRegisterPage;
