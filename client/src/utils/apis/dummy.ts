// QR 목록
export const DUMMY_QRLIST = [
	{
		qrCodeId: 1,
		title: '프라이빗 객실',
		accountNumber: '11345678915',
		amount: 20000,
	},
	{
		qrCodeId: 2,
		title: '일반 객실',
		accountNumber: '321561235',
		amount: 10000,
	},
];

export const DUMMY_SETTLEMENT_GROUP_LIST = [
	{
		qrCodeId: 1,
		qrCodeTitle: '럭셔리 글램핑 객실이용',
		amount: 20000,
		remainTotal: 60000,
		remainCount: 3,
	},
	{
		qrCodeId: 2,
		qrCodeTitle: '럭셔리 글램핑 2호점 객실이용',
		amount: 30000,
		remainTotal: 0,
		remainCount: 0,
	},
];

// 보증금 정산관리 상세 더미
export const DUMMY_DEPOSIT_DETAIL_RESPONSE = {
	code: 200,
	status: 'OK',
	message: 'SUCCESS',
	data: {
		hasNextPage: false,
		qrCodeTitle: '럭셔리 글램핑 객실이용',
		amount: 20000,
		totalAmount: 40000,
		waitCount: 1,
		doneCount: 1,
		waitDetails: [
			{
				tradeId: 2,
				status: 'WAIT',
				memberName: '홍진식',
				tradeDate: '2023-08-24 02:30',
			},
		],
		doneDetails: [
			{
				tradeId: 1,
				status: 'DONE',
				memberName: '임우택',
				tradeDate: '2023-08-21 10:35',
			},
		],
	},
};
